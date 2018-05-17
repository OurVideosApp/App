package com.lee.myghost.mvp.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.lee.myghost.R;
import com.lee.myghost.mvp.model.base.BaseAvtivity;
import com.lee.myghost.mvp.model.contract.viewinter.GetDataFromNetInter;
import com.lee.myghost.mvp.presenter.GetDataPresenter;
import com.lee.myghost.mvp.view.fragments.Fragment_My;
import com.lee.myghost.mvp.view.fragments.Fragment_Sift;
import com.lee.myghost.mvp.view.fragments.Fragment_Speical;
import com.lee.myghost.mvp.view.fragments.Fragment_Find;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class MainActivity extends BaseAvtivity<GetDataPresenter> implements GetDataFromNetInter, RadioGroup.OnCheckedChangeListener {

    private FrameLayout main_framlayout;
    private RadioGroup main_radiogroup;
    private RadioButton check_sift;
    private RadioButton check_special;
    private RadioButton check_find;
    private RadioButton check_my;
    private FragmentManager manager;
    private FragmentTransaction transaction;
    private Fragment_Sift fragment_sift;
    private Fragment_Speical fragment_speical;
    private Fragment_My fragment_my;
    private Fragment_Find fragment_find;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "setChildContentView: "+"执行onCreate");

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
        transaction = manager.beginTransaction();
        fragment_sift = new Fragment_Sift();
        transaction.add(R.id.main_framlayout, fragment_sift);
        transaction.commit();
        main_radiogroup.setOnCheckedChangeListener(this);
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

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId){
            case R.id.check_sift:
                FragmentTransaction transaction_sift = manager.beginTransaction();
                hideAll(transaction_sift);
                if (fragment_sift!=null){
                    transaction_sift.show(fragment_sift);
                }else {
                    fragment_sift=new Fragment_Sift();
                    transaction_sift.add(R.id.main_framlayout,fragment_sift);
                }
                transaction_sift.commit();
                break;
            case R.id.check_special:
                FragmentTransaction transaction_special = manager.beginTransaction();
                hideAll(transaction_special);
                if (fragment_speical!=null){
                    transaction_special.show(fragment_speical);
                }else {
                    fragment_speical = new Fragment_Speical();
                    transaction_special.add(R.id.main_framlayout,fragment_speical);
                }
                transaction_special.commit();
                break;
            case R.id.check_find:
                FragmentTransaction transaction_find = manager.beginTransaction();
                hideAll(transaction_find);
                if (fragment_find!=null){
                    transaction_find.show(fragment_find);
                }else {
                    fragment_find = new Fragment_Find();
                    transaction_find.add(R.id.main_framlayout, fragment_find);
                }
                transaction_find.commit();
                break;
            case R.id.check_my:
                FragmentTransaction transaction_my = manager.beginTransaction();
                hideAll(transaction_my);
                if (fragment_my!=null){
                    transaction_my.show(fragment_my);
                }else {
                    fragment_my = new Fragment_My();
                    transaction_my.add(R.id.main_framlayout, fragment_my);
                }
                transaction_my.commit();
                break;
        }
    }
    private void hideAll(FragmentTransaction fragment){
        if (fragment==null){
            return;
        }
        if (fragment_sift!=null){
            fragment.hide(fragment_sift);
        }
        if (fragment_speical!=null){
            fragment.hide(fragment_speical);
        }
        if (fragment_find!=null){
            fragment.hide(fragment_find);
        }
        if (fragment_my!=null){
            fragment.hide(fragment_my);
        }
    }
}
