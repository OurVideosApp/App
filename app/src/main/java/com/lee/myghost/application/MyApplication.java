package com.lee.myghost.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Process;
import android.os.StrictMode;

/**
 * @author Lee
 * @create_time 2018/5/16 19:58
 * @description Application
 */
public class MyApplication extends Application {
    public static int     myTid;
    public static Handler handler;
    public static Context context;


    @SuppressLint("NewApi")
    @Override
    public void onCreate() {
        super.onCreate();
        myTid = Process.myTid();
        handler = new Handler();
        context = getApplicationContext();

        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
    }

    //获取应用上下文环境
    public static Context getContext() {
        return context;
    }

    //获取主线程的id
    public static int getMainThreadId() {
        return myTid;
    }

    //获取handler
    public static Handler getAppHanler() {
        return handler;
    }

    public static Context getAppContext() {
        return context;
    }
}
