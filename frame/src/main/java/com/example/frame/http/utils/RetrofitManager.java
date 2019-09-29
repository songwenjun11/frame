package com.example.frame.http.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.frame.OverLication;
import com.example.frame.configs.NetConfig;
import com.example.frame.http.INetService;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Song Wenjun
 * Created by dell on 2019/2/23 11:19
 * Created prepare
 * package is com.example.frame.http.utils
 * <p>
 * This class is used to do:
 */
public class RetrofitManager {
    private RetrofitManager() {

    }

    public static RetrofitManager retrofitManager;

    public static RetrofitManager getInstance() {
        if (retrofitManager == null) {
            synchronized (RetrofitManager.class) {
                if (retrofitManager == null) {
                    retrofitManager = new RetrofitManager();
                }
            }
        }
        return retrofitManager;
    }

    public <T> INetService getNetService() {
        return new Retrofit.Builder()
                .baseUrl(NetConfig.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .client(getClientWithoutCache())
                .build()
                .create(INetService.class);
    }

    public OkHttpClient getClientWithoutCache() {
        return new OkHttpClient.Builder()
                //设置缓存路径,设置大小
                .cache(new Cache(new File(OverLication.lication.getCacheDir(), "HttpCache"), 10 * 1024 * 1024))
                .connectTimeout(6 * 1000, TimeUnit.SECONDS)
                .readTimeout(6, TimeUnit.SECONDS)
                .writeTimeout(6, TimeUnit.SECONDS)
                .addInterceptor(getLogInterceptor())//添加Ok拦截器
                //                .addInterceptor(cacheInterceptor(FramApplication.getContext()))
                //                .addNetworkInterceptor(cacheInterceptor(FramApplication.getContext()))
                .build();
    }

    /**
     * 网络请求log拦截器
     *
     * @return log拦截器对象
     */

    public static HttpLoggingInterceptor getLogInterceptor() {
        //设置log拦截器拦截内容
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        //新建log拦截器
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.e("------retrofit-------", message);
            }
        });
        loggingInterceptor.setLevel(level);
        return loggingInterceptor;
    }

    /**
     * 网络优先数据缓存拦截器
     *
     * @return 拦截器对象
     */
    public static Interceptor cacheInterceptor(final Context context) {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();//获取请求
                //没有网络的时候强制使用缓存
                if (!isNetworkAvailable(context)) {
                    request = request.newBuilder()
                            .cacheControl(CacheControl.FORCE_CACHE)
                            .build();
                    Log.e("睚眦", "没网读取缓存");
                }
                Response response = chain.proceed(request);
                if (isNetworkAvailable(context)) {
                    return response.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public,max-age" + 0)
                            .build();
                } else {
                    int maxTime = 4 * 24 * 60 * 30;
                    return response.newBuilder()
                            .removeHeader("Pragma")
                            .header("Cache-Control", "public,only-if-cached,max-state=" + maxTime)
                            .build();
                }
            }
        };
        return interceptor;
    }

    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            //ACCESS_NETWORK_STATE
            NetworkInfo info = connectivity.getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                // 当前网络是连接的
                if (info.getState() == NetworkInfo.State.CONNECTED) {
                    // 当前所连接的网络可用
                    return true;
                }
            }
        }
        return false;
    }
}
