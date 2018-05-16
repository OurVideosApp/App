package com.lee.myghost.mvp.model.base;

import com.lee.myghost.mvp.model.contract.presenterinter.PresenterInter;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public abstract class BasePresenter<T> implements PresenterInter{
    protected Reference<T> mViewRef;//View接口类型弱引用

    public void attachView(T View) {
        mViewRef = new WeakReference<T>(View); //建立关联
    }

    protected T getView() {
        return mViewRef.get();//获取View
    }

    public boolean isViewAttached() {//判断是否与View建立了关联
        return mViewRef != null && mViewRef.get() != null;
    }

    public void detachView() {//解除关联
        if (mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }
}