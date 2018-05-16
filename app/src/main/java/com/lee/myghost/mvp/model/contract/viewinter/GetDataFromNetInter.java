package com.lee.myghost.mvp.model.contract.viewinter;

import okhttp3.ResponseBody;

public interface GetDataFromNetInter {
    void onSuccess(ResponseBody responseBody);
    void onError(Throwable throwable);
}