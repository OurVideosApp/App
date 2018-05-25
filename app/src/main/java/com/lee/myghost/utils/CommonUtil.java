package com.lee.myghost.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Process;

import com.lee.myghost.application.MyApplication;


/**
 * author: 晨光光
 * date : 2018/5/18 17:13
 */
public class CommonUtil {

    private static SharedPreferences sharedPreferences;

    public static void runUiThread(Runnable runnable) {
        if (Process.myTid() == MyApplication.myTid) {
            runnable.run();
        } else {
            MyApplication.getAppHanler().post(runnable);
        }
    }


    private static void getSharePreference() {
        if (sharedPreferences == null)
            sharedPreferences = MyApplication.getAppContext().getSharedPreferences("cgShared", Context.MODE_PRIVATE);
    }

    public static void saveColorValue(int color) {
        getSharePreference();
        sharedPreferences.edit().putInt(SharedKey.COLOR_KEY, color).commit();
    }

    public static int obtainColorValue() {
        getSharePreference();
        return sharedPreferences.getInt(SharedKey.COLOR_KEY, -1);
    }
}
