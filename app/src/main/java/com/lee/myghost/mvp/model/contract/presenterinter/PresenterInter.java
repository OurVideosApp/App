package com.lee.myghost.mvp.model.contract.presenterinter;

import java.util.Map;

import okhttp3.ResponseBody;

/**
 * @author Lee
 * @create_time 2018/5/16 19:56
 * @description Presenter的接口
 */
public interface PresenterInter {

    void getDataFromNet(String url, Map<String, String> map);

    void onSuccess(ResponseBody responseBody);

    void onError(Throwable e);

    void unsubcribe();
}
