package com.example.frame.classimp;

import android.app.Application;
import android.util.Log;

import io.reactivex.Observable;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import com.example.frame.OverLication;
import com.example.frame.bean.Users;
import com.example.frame.configs.ApiConfig;
import com.example.frame.configs.RefreshConfig;
import com.example.frame.configs.RequestType;
import com.example.frame.http.INetService;
import com.example.frame.http.utils.HttpUtils;
import com.example.frame.http.utils.RetrofitManager;
import com.example.frame.http.utils.SharedPrefrenceUtils;
import com.example.frame.interfaces.ICommonModule;
import com.example.frame.interfaces.ICommonView;

import java.io.File;
import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;


/**
 * Song Wenjun
 * Created by dell on 2019/2/23 10:54
 * Created prepare
 * package is com.example.frame.classimp
 * <p>
 * This class is used to do:
 */
public class CommonModule implements ICommonModule {
    /**
     * 进行普通的get请求
     *
     * @param presenter     传入一个p层实现类
     * @param refreshConfig 传入什么类型的请求
     * @param apiConfig     传入第几次请求这个网络
     * @param t             传入其他参数，可变参
     */
    @Override
    public void getData(ICommonView presenter, RefreshConfig refreshConfig, ApiConfig apiConfig, Object... t) {
        INetService service = RetrofitManager.getInstance().getNetService();
        //t[1]传入访问网址，t[2]传入参数集合Map<String,Object>
        Observable<String> data = service.getData(String.valueOf(t[0]), t[1] != null ? (Map<String, Object>) t[1] : null);
        HttpUtils.getRespone(data, presenter, refreshConfig, apiConfig, t);
    }

    /**
     * 进行普通的post请求
     *
     * @param presenter 传入p层实现类
     * @param apiConfig 传入第几次请求
     * @param t
     */
    @Override
    public void postData(ICommonView presenter, RefreshConfig refreshConfig, ApiConfig apiConfig, Object... t) {
        INetService netService = RetrofitManager.getInstance().getNetService();
        //t[1]传入访问网址，t[2]传入参数集合Map<String,Object>
        Observable<String> postData = netService.postData(String.valueOf(t[0]), (Map<String, Object>) t[1]);
        HttpUtils.getRespone(postData, presenter, refreshConfig, apiConfig, t);
    }

    /**
     * 进行from请求
     *
     * @param presenter
     * @param apiConfig
     * @param t
     */
    @Override
    public void fromData(ICommonView presenter, RefreshConfig refreshConfig, ApiConfig apiConfig, Object... t) {

    }

    /**
     * 进行文件提交请求
     *
     * @param presenter
     * @param t
     */
    @Override
    public void fileCommit(ICommonView presenter, RefreshConfig refreshConfig, ApiConfig apiConfig, Object... t) {
        INetService netService = RetrofitManager.getInstance().getNetService();
        //t[2]文件路径
        File file = null;
        if (t[1] != null)
            if (t[1] instanceof File) {
                file = (File) t[1];
            } else {
                file = new File(String.valueOf(t[1]));
            }
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);
        if (file != null) {
            RequestBody body = RequestBody.create(MediaType.parse("image/*"), file);
            //t[4]服务器接收文件的字段
            builder.addFormDataPart(String.valueOf(t[3]), file.getName(), body);
        }
        Map<String, Object> map = null;
        //t[3]传入的其他需要的参数
        if (t[2] instanceof Map) {
            map = (Map<String, Object>) t[2];
            Iterator<Map.Entry<String, Object>> it = map.entrySet().iterator();
            while (it.hasNext()) {
                Map.Entry<String, Object> entry = it.next();
                builder.addFormDataPart(entry.getKey(), String.valueOf(entry.getValue()));
            }
        }
        //t[1]传入服务器网址(文件提交网址)
        Users user = SharedPrefrenceUtils.getObject(OverLication.lication, "user");
        Observable<String> fileCommit = netService.fileCommit(String.valueOf(t[0]), builder.build(), user != null ? user.getNumber() + "" : "12312");
        HttpUtils.getRespone(fileCommit, presenter, refreshConfig, apiConfig, t);
    }

    /**
     * json提交的请求
     *
     * @param presenter
     * @param json      需要提交的json串
     * @param apiConfig
     * @param t
     */
    @Override
    public void jsonData(ICommonView presenter, String json, RefreshConfig refreshConfig, ApiConfig apiConfig, Object... t) {
        INetService netService = RetrofitManager.getInstance().getNetService();
        RequestBody body = RequestBody.create(MediaType.parse("application/json,charset-UTF-8"), json);
        //t[1]传入访问网址
        Observable<String> stringObservable = netService.jsonData(String.valueOf(t[0]), body);
        HttpUtils.getRespone(stringObservable, presenter, refreshConfig, apiConfig, t);
    }
}
