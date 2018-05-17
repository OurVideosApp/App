package com.lee.myghost.utils;

import android.util.Log;

import com.lee.myghost.api.ApiService;
import com.lee.myghost.api.Constant;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtil {
    private static RetrofitUtil retrofitUtil = null;
    private Retrofit retrofit;

    private RetrofitUtil() {
    }

    private RetrofitUtil(String baseUrl) {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor( new CommonParamsInterceptor() )
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl( baseUrl )
                .client( okHttpClient )
                .addConverterFactory( GsonConverterFactory.create() )
                .addCallAdapterFactory( RxJava2CallAdapterFactory.create() )
                .build();
    }

    public static RetrofitUtil getInstance(String baseUrl) {
        if (retrofitUtil == null) {
            synchronized (RetrofitUtil.class) {
                if (retrofitUtil == null) {
                    retrofitUtil = new RetrofitUtil( baseUrl );
                }
            }
        }
        return retrofitUtil;
    }

    public <T> T createService(Class<T> service) {
        return retrofit.create( service );
    }

    public static ApiService getService() {
        return RetrofitUtil.getInstance( Constant.BASE_URL ).createService( ApiService.class );
    }

    private static class CommonParamsInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            String method = request.method();
            String oldUrl = request.url().toString();
            Log.e( "----oldUrl", oldUrl );
            Map<String, String> map = new HashMap<>();


            if ("GET".equals( method )) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append( oldUrl );
                if (oldUrl.contains( "?" )) {
                    if (oldUrl.indexOf( "?" ) == oldUrl.length() - 1) {
                    } else {
                        stringBuilder.append( "&" );
                    }
                } else {
                    stringBuilder.append( "?" );
                }
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    stringBuilder.append( entry.getKey() )
                            .append( "=" )
                            .append( entry.getValue() )
                            .append( "&" );
                }
                if (stringBuilder.indexOf( "&" ) != -1) {
                    stringBuilder.deleteCharAt( stringBuilder.lastIndexOf( "&" ) );
                }
                String newUrl = stringBuilder.toString();
                Log.e( "----newUrl", newUrl );
                request = request.newBuilder().url( newUrl ).build();
            } else if ("POST".equals( method )) {
                RequestBody requestBody = request.body();
                if (requestBody instanceof FormBody) {
                    FormBody oldBody = (FormBody) requestBody;
                    FormBody.Builder builder = new FormBody.Builder();
                    for (int i = 0; i < oldBody.size(); i++) {
                        builder.add( oldBody.name( i ), oldBody.value( i ) );
                    }
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        builder.add( entry.getKey(), entry.getValue() );
                    }
                    request = request.newBuilder().url( oldUrl ).post( builder.build() ).build();
                } else if (requestBody instanceof MultipartBody) {
                    //因为传送文件要用到 @Multipart注解
                    MultipartBody bodyM = (MultipartBody) request.body();
                    MultipartBody.Builder builder = new MultipartBody.Builder().setType( MultipartBody.FORM );

                    //添加公共参数
                    for (Map.Entry<String, String> entry : map.entrySet()) {
                        Log.e( "---" + entry.getKey(), entry.getValue() );

                        builder.addFormDataPart( entry.getKey(), entry.getValue() );

                    }

                    //原来的添加上
                    List<MultipartBody.Part> parts = bodyM.parts();
                    for (MultipartBody.Part part : parts) {
                        builder.addPart( part );
                    }


                    request = request.newBuilder().url( oldUrl ).post( builder.build() ).build();

                }
            }
            Response response = chain.proceed( request );
            return response;
        }
    }
}