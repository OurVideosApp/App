package com.lee.myghost.application;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Handler;
import android.os.Process;
import android.os.StrictMode;

import com.lee.myghost.mvp.view.db.DaoMaster;
import com.lee.myghost.mvp.view.db.DaoSession;
import com.lee.myghost.utils.DensityHelper;
import com.lee.myghost.utils.SharedPreferencesHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Lee
 * @create_time 2018/5/16 19:58
 * @description Application
 */
public class MyApplication extends Application {
    public final static float DESIGN_WIDTH = 720;
    public static  int                     myTid;
    public static  Handler                 handler;
    public static  Context                 context;
    private static MyApplication           instance;
    private static SharedPreferencesHelper sharedPreferencesHelper;
    /**
     * 打开的activity
     **/
    private List<Activity> activities = new ArrayList<Activity>();
    private DaoMaster.DevOpenHelper mHelper;
    private SQLiteDatabase db;
    private DaoMaster mDaoMaster;
    private DaoSession mDaoSession;

    @SuppressLint("NewApi")
    @Override
    public void onCreate() {
        super.onCreate();
        myTid = Process.myTid();
        handler = new Handler();
        context = getApplicationContext();
        instance = this;
        // android 7.0系统解决拍照的问题
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();

        new DensityHelper(this, DESIGN_WIDTH).activate();  //DESIGN_WIDTH为设计图宽度，同样不要忘记清单文件配置Application，另 布局中使用pt
        setDatabase();
    }

    private void setDatabase() {
        mHelper = new DaoMaster.DevOpenHelper(this, "notes-db", null);
        db = mHelper.getWritableDatabase();
        mDaoMaster = new DaoMaster(db);
        mDaoSession = mDaoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return mDaoSession;
    }
    public SQLiteDatabase getDb() {
        return db;
    }

    /**
     * 获得实例
     *
     * @return
     */
    public static MyApplication getInstance() {
        return instance;
    }

    public static SharedPreferencesHelper getSharedPreferenceHelper() {
        if (sharedPreferencesHelper == null) {
            return new SharedPreferencesHelper(instance);
        } else {
            return sharedPreferencesHelper;
        }
    }

    /**
     * 新建了一个activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    /**
     * 结束指定的Activity
     *
     * @param activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            this.activities.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 应用退出，结束所有的activity
     */
    public void exit() {
        for (Activity activity : activities) {
            if (activity != null) {
                activity.finish();
            }
        }
        System.exit(0);
    }

    /**
     * 关闭Activity列表中的所有Activity
     */
    public void finishActivity() {

        for (Activity activity : activities) {
            if (null != activity) {
                activity.finish();
            }
        }
        //杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
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
