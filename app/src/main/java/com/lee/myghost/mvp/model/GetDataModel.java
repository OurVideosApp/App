package com.lee.myghost.mvp.model;

import android.util.Log;

import com.lee.myghost.mvp.model.contract.modelinter.BaseModel;
import com.lee.myghost.mvp.model.contract.presenterinter.PresenterInter;
import com.lee.myghost.mvp.presenter.GetDataPresenter;
import com.lee.myghost.utils.RetrofitUtil;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

/**
 * @author Lee
 * @create_time 2018/5/16 20:01
 * @description 请求网络的Model层
 */
public class GetDataModel implements BaseModel {
    private PresenterInter presenterInter;
    private Disposable     d;

    public GetDataModel(PresenterInter presenterInter) {
        this.presenterInter = presenterInter;
        this.d = d;
    }

    @Override
    public void getDataFromNet(String url, final Map<String, String> map) {
        Log.d("Lee", "getDataFromNet: " + map);
        RetrofitUtil.getService().doPost(url, map).subscribeOn(Schedulers.io())//在io线程获取数据
                .observeOn(AndroidSchedulers.mainThread())//在android主线程梳理数据
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        GetDataModel.this.d = d;
                    }

                    @Override
                    public void onNext(ResponseBody responseBody) {
                        //代表获取到数据
                        presenterInter.onSuccess(responseBody);
                        Log.d("Lee", "onNext: " + map.toString());
                    }

                    @Override
                    public void onError(Throwable e) {
                        //发生错误
                        presenterInter.onError(e);
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void unsubcribe() {
        d.dispose();
    }
}
