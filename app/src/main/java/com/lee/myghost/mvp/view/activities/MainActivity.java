package com.lee.myghost.mvp.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.lee.myghost.R;
import com.lee.myghost.mvp.model.base.BaseAvtivity;
import com.lee.myghost.mvp.model.contract.viewinter.GetDataFromNetInter;
import com.lee.myghost.mvp.presenter.GetDataPresenter;

import okhttp3.ResponseBody;

public class MainActivity extends BaseAvtivity<GetDataPresenter> implements GetDataFromNetInter {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "setChildContentView: "+"执行onCreate");

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public GetDataPresenter setPresenter() {
        return new GetDataPresenter(this);
    }

    @Override
    public int setChildContentView() {
        Log.d("TAG", "setChildContentView: "+"执行");
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(ResponseBody responseBody) {

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
