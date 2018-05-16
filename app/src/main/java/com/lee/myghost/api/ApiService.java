package com.lee.myghost.api;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author Lee
 * @create_time 2018/5/11 16:03
 * @description
 */
public interface ApiService {

    @FormUrlEncoded
    @POST("{url}")
    Observable<ResponseBody> doPost(@Path(value = "url", encoded = true) String url, @FieldMap Map<String, String> map);
}
