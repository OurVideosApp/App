package com.lee.myghost.mvp.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.lee.myghost.R;
import com.lee.myghost.mvp.model.base.BaseAvtivity;
import com.lee.myghost.mvp.presenter.GetDataPresenter;

public class SearchActivity extends BaseAvtivity<GetDataPresenter> {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int setChildContentView() {
        return R.layout.activity_search;
    }

    @Override
    public GetDataPresenter setPresenter() {
        return null;
    }
}
