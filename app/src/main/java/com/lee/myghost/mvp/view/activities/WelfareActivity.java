package com.lee.myghost.mvp.view.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.lee.myghost.R;
import com.lee.myghost.api.Constant;
import com.lee.myghost.mvp.model.bean.Welfarebean;
import com.lee.myghost.mvp.view.adapters.WelfareAdapter;
import com.lee.myghost.utils.OkHttp3Util;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class WelfareActivity extends AppCompatActivity {


    private RecyclerView weifare_list_rlv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welfare);
        weifare_list_rlv = findViewById(R.id.weifare_list_rlv);

        weifare_list_rlv.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        OkHttp3Util.doGet(Constant.BASE_URL1 + Constant.MATERIAL_BENEFITS_URL, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, final Response response){
                try {
                    final String string = response.body().string();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            Welfarebean welfarebean = new Gson().fromJson(string, Welfarebean.class);
                            WelfareAdapter welfareAdapter = new WelfareAdapter(WelfareActivity.this,welfarebean);
                            weifare_list_rlv.setAdapter(welfareAdapter);
                        }
                    });

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
