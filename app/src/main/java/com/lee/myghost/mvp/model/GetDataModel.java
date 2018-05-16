package com.lee.myghost.mvp.model;

import com.lee.myghost.mvp.model.contract.modelinter.BaseModel;
import com.lee.myghost.mvp.model.contract.presenterinter.PresenterInter;
import com.lee.myghost.mvp.presenter.GetDataPresenter;

import java.util.Map;

import io.reactivex.disposables.Disposable;

/**
 * @author Lee
 * @create_time 2018/5/16 20:01
 * @description
 */
public class GetDataModel implements BaseModel {
    private PresenterInter presenterInter;
    private Disposable     d;

    public GetDataModel(GetDataPresenter presenter) {

    }

    @Override
    public void getDataFromNet(String url, Map<String, String> map) {

    }

    @Override
    public void unsubcribe() {

    }
}
