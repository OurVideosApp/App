package com.lee.myghost.mvp.model.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * @author Lee
 * @create_time 2018/5/16 19:37
 * @description Activity父类
 */
public abstract class BaseAvtivity<T extends BasePresenter> extends AppCompatActivity {

    public T presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);

    }

    //初始化view
    public abstract void initView();

    //初始化数据
    public abstract void initData();

    //设置布局
    public abstract int setChildContentView();

    public abstract T setPresenter();
}
