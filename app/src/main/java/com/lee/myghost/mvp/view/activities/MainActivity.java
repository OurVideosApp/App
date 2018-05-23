package com.lee.myghost.mvp.view.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lee.myghost.R;
import com.lee.myghost.mvp.model.base.BaseAvtivity;
import com.lee.myghost.mvp.model.contract.viewinter.GetDataFromNetInter;
import com.lee.myghost.mvp.presenter.GetDataPresenter;
import com.lee.myghost.mvp.view.fragments.FindFragment;
import com.lee.myghost.mvp.view.fragments.MineFragment;
import com.lee.myghost.mvp.view.fragments.ChoicenessFragment;
import com.lee.myghost.mvp.view.fragments.SpeicalFragment;

import okhttp3.ResponseBody;

public class MainActivity extends BaseAvtivity<GetDataPresenter> implements GetDataFromNetInter, RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    private FrameLayout main_framlayout;
    private RadioGroup main_radiogroup;
    private RadioButton check_sift;
    private RadioButton check_special;
    private RadioButton check_find;
    private RadioButton check_my;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private ChoicenessFragment mChoicenessFragment;
    private SpeicalFragment mSpeicalFragment;
    private MineFragment mMineFragment;
    private FindFragment mFindFragment;
    private RelativeLayout my_download, my_collect, my_welfare, my_share, my_suggest, slide_about, slide_theme;
    private Intent intent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "setChildContentView: " + "执行onCreate");
    }

    @Override
    public void initView() {
        main_framlayout = findViewById(R.id.main_framlayout);
        main_radiogroup = findViewById(R.id.main_radiogroup);
        check_sift = findViewById(R.id.check_sift);
        check_special = findViewById(R.id.check_special);
        check_find = findViewById(R.id.check_find);
        check_my = findViewById(R.id.check_my);
        manager = getSupportFragmentManager();
        mChoicenessFragment = new ChoicenessFragment();
        main_radiogroup.setOnCheckedChangeListener(this);
        my_collect = findViewById(R.id.my_collect);
        my_download = findViewById(R.id.my_download);
        my_welfare = findViewById(R.id.my_welfare);
        my_share = findViewById(R.id.my_share);
        my_suggest = findViewById(R.id.my_suggest);
        slide_about = findViewById(R.id.slide_about);
        slide_theme = findViewById(R.id.slide_theme);
        my_collect.setOnClickListener(this);
        my_download.setOnClickListener(this);
        my_welfare.setOnClickListener(this);
        my_share.setOnClickListener(this);
        my_suggest.setOnClickListener(this);
        slide_about.setOnClickListener(this);
        slide_theme.setOnClickListener(this);
    }

    @Override
    public void initData() {
        transaction = manager.beginTransaction();
        transaction.add(R.id.main_framlayout, mChoicenessFragment);
        transaction.commit();
    }

    @Override
    public GetDataPresenter setPresenter() {
        return new GetDataPresenter(this);
    }

    @Override
    public int setChildContentView() {
        Log.d("TAG", "setChildContentView: " + "执行");
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(ResponseBody responseBody) {

    }

    @Override
    public void onError(Throwable throwable) {
        Log.d("MainActivity", "throwable:" + throwable);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.check_sift:
                FragmentTransaction transaction_sift = manager.beginTransaction();
                hideAll(transaction_sift);
                if (mChoicenessFragment != null) {
                    transaction_sift.show(mChoicenessFragment);
                } else {
                    mChoicenessFragment = new ChoicenessFragment();
                    transaction_sift.add(R.id.main_framlayout, mChoicenessFragment);
                }
                transaction_sift.commit();
                break;
            case R.id.check_special:
                FragmentTransaction transaction_special = manager.beginTransaction();
                hideAll(transaction_special);
                if (mSpeicalFragment != null) {
                    transaction_special.show(mSpeicalFragment);
                } else {
                    mSpeicalFragment = new SpeicalFragment();
                    transaction_special.add(R.id.main_framlayout, mSpeicalFragment);
                }
                transaction_special.commit();
                break;
            case R.id.check_find:
                FragmentTransaction transaction_find = manager.beginTransaction();
                hideAll(transaction_find);
                if (mFindFragment != null) {
                    transaction_find.show(mFindFragment);
                } else {
                    mFindFragment = new FindFragment();
                    transaction_find.add(R.id.main_framlayout, mFindFragment);
                }
                transaction_find.commit();
                break;
            case R.id.check_my:
                FragmentTransaction transaction_my = manager.beginTransaction();
                hideAll(transaction_my);
                if (mMineFragment != null) {
                    transaction_my.show(mMineFragment);
                } else {
                    mMineFragment = new MineFragment();
                    transaction_my.add(R.id.main_framlayout, mMineFragment);
                }
                transaction_my.commit();
                break;
        }
    }

    private void hideAll(FragmentTransaction fragment) {
        if (fragment == null) {
            return;
        }
        if (mChoicenessFragment != null) {
            fragment.hide(mChoicenessFragment);
        }
        if (mSpeicalFragment != null) {
            fragment.hide(mSpeicalFragment);
        }
        if (mFindFragment != null) {
            fragment.hide(mFindFragment);
        }
        if (mMineFragment != null) {
            fragment.hide(mMineFragment);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_collect:
                intent = new Intent(MainActivity.this, CollectActivity.class);
                break;
            case R.id.my_download:
                Toast.makeText(MainActivity.this,"待开发",Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_welfare:
                intent = new Intent(MainActivity.this, WelfareActivity.class);
                break;
            case R.id.my_share:
                intent = new Intent(MainActivity.this, ShareActivity.class);
                break;
            case R.id.my_suggest:
                intent = new Intent(MainActivity.this, SuggestActivity.class);
                break;
            case R.id.slide_about:
                intent = new Intent(MainActivity.this, AboutActivity.class);
                break;
            case R.id.slide_theme:
                intent = new Intent(MainActivity.this, ThemeActivity.class);
                break;
        }
        startActivity(intent);
    }
}
