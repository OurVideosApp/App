package com.lee.myghost;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.lee.myghost.mvp.model.base.BaseAvtivity;
import com.lee.myghost.mvp.presenter.GetDataPresenter;

public class MainActivity extends BaseAvtivity<GetDataPresenter> {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public GetDataPresenter setPresenter() {
        return new GetDataPresenter();
    }

    @Override
    public int setChildContentView() {
        return 0;
    }
}
