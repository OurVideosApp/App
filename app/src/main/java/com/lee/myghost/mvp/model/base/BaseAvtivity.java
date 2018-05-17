package com.lee.myghost.mvp.model.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.lee.myghost.application.MyApplication;

/**
 * @author Lee
 * @create_time 2018/5/16 19:37
 * @description Activity父类
 */
public abstract class BaseAvtivity<T extends BasePresenter> extends AppCompatActivity {

    public T presenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MyApplication.getInstance().addActivity(this);
        setContentView(setChildContentView());
        initView();
        initBaseData();
        initData();
    }

    private void initBaseData() {
        presenter = setPresenter();
        if (presenter != null) {
            presenter.attachView(this);
        } else {
            try {
                throw new Exception("老兄 prenter 没有设置 请在您的Activity 创建 presenter");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    };

    //初始化view
    public abstract void initView();

    //初始化数据
    public abstract void initData();

    //设置布局
    public abstract int setChildContentView();

    public abstract T setPresenter();
}
