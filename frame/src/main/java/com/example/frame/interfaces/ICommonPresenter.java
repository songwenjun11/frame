package com.example.frame.interfaces;

import com.example.frame.configs.ApiConfig;
import com.example.frame.configs.RefreshConfig;

/**
 * Song Wenjun
 * Created by dell on 2019/2/23 10:50
 * Created prepare
 * package is com.example.frame
 * <p>
 * This class is used to do:用来连接module和view
 */
public interface ICommonPresenter<T> {
    /**
     * 这个是m层和v层之间的万能接口,主要用来连接module和view
     *
     * @param refreshConfig
     * @param apiConfig
     * @param t
     */
    void universalNode(RefreshConfig refreshConfig, ApiConfig apiConfig, T... t);
}
