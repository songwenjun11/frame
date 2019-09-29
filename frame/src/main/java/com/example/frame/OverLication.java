package com.example.frame;

import android.app.Application;
import android.content.Context;

/**
 * Song Wenjun
 * Created by dell on 2019/2/23 11:23
 * Created prepare
 * package is com.example.frame.http.utils
 * <p>
 * This class is used to do:
 */
public class OverLication extends Application {
    public static Context lication;

    @Override
    public void onCreate() {
        super.onCreate();
        lication = this;
    }
}
