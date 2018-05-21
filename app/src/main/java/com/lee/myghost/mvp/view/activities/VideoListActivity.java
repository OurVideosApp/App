package com.lee.myghost.mvp.view.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lee.myghost.R;
import com.lee.myghost.api.Constant;
import com.lee.myghost.mvp.model.bean.VideoListBean;
import com.lee.myghost.mvp.model.bean.VideoList_Bean;
import com.lee.myghost.mvp.model.contract.viewinter.GetDataFromNetInter;
import com.lee.myghost.mvp.presenter.GetDataPresenter;
import com.lee.myghost.mvp.view.adapters.VideoListAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

public class VideoListActivity extends AppCompatActivity implements GetDataFromNetInter {

    private GetDataPresenter getDataPresenter;
    private RecyclerView video_list_tiaomu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_list);
        video_list_tiaomu = findViewById(R.id.video_list_tiaomu);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String name = intent.getStringExtra("name");
        TextView video_list_title = findViewById(R.id.video_list_title);
        TextView back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        video_list_title.setText(name);
        String[] split = url.split("\\?");
        Log.e("wang", split[1].toString()+"123456");
        String[] split1 = split[1].split("\\=");
        Log.e("wang", split1[1].toString()+"123456");
        GridLayoutManager layoutmanager = new GridLayoutManager(this,3);
        //设置RecyclerView 布局
        video_list_tiaomu.setLayoutManager(layoutmanager);
        getDataPresenter = new GetDataPresenter(this);
        Map<String, String> map=new HashMap<>();
        map.put(split1[0],split1[1]);
        getDataPresenter.getDataFromNet(" front/videoDetailApi/videoDetail.do",map);
    }

    @Override
    public void onSuccess(ResponseBody responseBody) {
        try {
            String string  = responseBody.string();
            VideoListBean videoListBean = new Gson().fromJson(string, VideoListBean.class);
            VideoListAdapter videoListAdapter = new VideoListAdapter(videoListBean, this);
            video_list_tiaomu.setAdapter(videoListAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onError(Throwable throwable) {

    }
}
