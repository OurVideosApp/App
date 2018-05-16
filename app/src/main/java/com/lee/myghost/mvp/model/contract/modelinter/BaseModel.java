package com.lee.myghost.mvp.model.contract.modelinter;

import java.util.Map;

public interface BaseModel {
    void getDataFromNet(String url, Map<String, String> map);

    void unsubcribe();
}