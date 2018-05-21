package com.lee.myghost.mvp.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lee.myghost.R;
import com.lee.myghost.mvp.model.base.BaseAvtivity;
import com.lee.myghost.mvp.model.contract.viewinter.GetDataFromNetInter;
import com.lee.myghost.mvp.presenter.GetDataPresenter;
import com.lee.myghost.mvp.view.fragments.FindFragment;
import com.lee.myghost.mvp.view.fragments.MineFragment;
import com.lee.myghost.mvp.view.fragments.ChoicenessFragment;
import com.lee.myghost.mvp.view.fragments.SpeicalFragment;

import okhttp3.ResponseBody;

public class MainActivity extends BaseAvtivity<GetDataPresenter> implements GetDataFromNetInter, RadioGroup.OnCheckedChangeListener {

    private FrameLayout         main_framlayout;
    private RadioGroup          main_radiogroup;
    private RadioButton         check_sift;
    private RadioButton         check_special;
    private RadioButton         check_find;
    private RadioButton         check_my;
    private FragmentManager     manager;
    private FragmentTransaction transaction;
    private ChoicenessFragment  mChoicenessFragment;
    private SpeicalFragment     mSpeicalFragment;
    private MineFragment        mMineFragment;
    private FindFragment        mFindFragment;

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
}
