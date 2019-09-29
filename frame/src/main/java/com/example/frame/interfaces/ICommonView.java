package com.example.frame.interfaces;

import com.example.frame.configs.ApiConfig;
import com.example.frame.configs.RefreshConfig;
import com.example.frame.configs.RequestType;

/**
 * Song Wenjun
 * Created by dell on 2019/2/23 10:21
 * Created prepare
 * package is com.example.frame
 * <p>
 * This class is used to do:用来回调数据
 */
public interface ICommonView<T> {
    /**
     * 这个方法用来做回调使用
     *
     * @param refreshConfig 这个参数规定了 正常加载 刷新加载 加载更多
     * @param apiConfig   这个参数规定了这个请求是这个界面的第几次请求
     * @param t           返回一个不固定类型对象
     */
    void onRespone(RefreshConfig refreshConfig, ApiConfig apiConfig, T t);

    /**
     * 这个方法用来回调错误信息
     *
     * @param apiConfig 这个规定了是这个界面第几个请求回来的错误信息
     * @param e         这个就是返回的错误信息
     */
    void onError(ApiConfig apiConfig, String e);
}
