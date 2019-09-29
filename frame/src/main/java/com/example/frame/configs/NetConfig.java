package com.example.frame.configs;

/**
 * Song Wenjun
 * Created by dell on 2019/2/23 10:28
 * Created prepare
 * package is com.example.frame.configs
 * <p>
 * This class is used to do:这里进行切换网址通过改变apiType得值
 */
public class NetConfig {
    public static int apiType = 3;
    public static String BASE_URL;

    static {
        if (apiType == 1){
            BASE_URL = "http://192.168.1.107:8080/LoveBiz/";
        } else if (apiType == 2){
            BASE_URL = "http://jiekou.55555.io:18619/LoveBiz/";
        } else {
            BASE_URL = "http://www.songwenjun.top/LoveBiz/";
        }
    }
}
