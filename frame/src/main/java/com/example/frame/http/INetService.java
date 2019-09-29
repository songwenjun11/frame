package com.example.frame.http;

import io.reactivex.Observable;

import java.util.Map;

import okhttp3.RequestBody;
import retrofit2.http.Body;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

/**
 * Song Wenjun
 * Created by dell on 2019/2/23 11:00
 * Created prepare
 * package is com.example.frame.http
 * <p>
 * This class is used to do:
 */
public interface INetService {
    @GET
    Observable<String> getData(@Url String url, @QueryMap Map<String, Object> map);

    @POST
    @FormUrlEncoded
    Observable<String> postData(@Url String url, @FieldMap Map<String, Object> map);

    @POST
    Observable<String> jsonData(@Url String url, @Body RequestBody body);

    @POST
    Observable<String> fileCommit(@Url String url, @Body RequestBody body, @Header("name") String name);
}
