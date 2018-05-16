package com.lee.myghost.mvp.presenter;

import com.lee.myghost.mvp.model.GetDataModel;
import com.lee.myghost.mvp.model.base.BasePresenter;
import com.lee.myghost.mvp.model.contract.presenterinter.PresenterInter;
import com.lee.myghost.mvp.model.contract.viewinter.GetDataFromNetInter;

import java.util.Map;

import okhttp3.ResponseBody;

/**
 * @author Lee
 * @create_time 2018/5/16 19:44
 * @description
 */
public class GetDataPresenter extends BasePresenter {
    private GetDataModel        mGetDataModel;
    private GetDataFromNetInter mGetDataFromNetInter;

    public GetDataPresenter(GetDataFromNetInter getDataFromNetInter) {
        this.mGetDataFromNetInter = getDataFromNetInter;
        mGetDataModel = new GetDataModel(this);
    }

    @Override
    public void getDataFromNet(String url, Map<String, String> map) {
        mGetDataModel.getDataFromNet(url, map);
    }

    @Override
    public void onSuccess(ResponseBody responseBody) {
        mGetDataFromNetInter.onSuccess(responseBody);
    }

    @Override
    public void onError(Throwable e) {
        mGetDataFromNetInter.onError(e);
    }

    @Override
    public void unsubcribe() {
        mGetDataModel.unsubcribe();
    }
}
