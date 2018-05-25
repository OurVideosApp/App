package com.lee.myghost.mvp.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lee.myghost.BuildConfig;
import com.lee.myghost.R;
import com.lee.myghost.mvp.model.base.BaseAvtivity;
import com.lee.myghost.mvp.model.bean.VideoListBean;
import com.lee.myghost.mvp.model.contract.viewinter.GetDataFromNetInter;
import com.lee.myghost.mvp.presenter.GetDataPresenter;
import com.lee.myghost.mvp.view.adapters.VideoListAdapter;
import com.lee.myghost.utils.CommonUtil;
import com.lee.myghost.utils.FirstEvent;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;

public class VideoListActivity extends BaseAvtivity<GetDataPresenter> implements GetDataFromNetInter {

    private GetDataPresenter getDataPresenter;
    private RecyclerView     video_list_tiaomu;
    private RelativeLayout mVideo_list_title;
    private TextView         mBack;
    private String           mName;
    private String           mUrl;
    private VideoListAdapter videoListAdapter;
    private VideoListBean videoListBean;
    private TextView video_list_name;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        int colorValue = CommonUtil.obtainColorValue();
        if (colorValue!=-1){
            video_list_name.setBackgroundColor(colorValue);
        }
    }

    @Override
    public void initView() {
        getDataPresenter = new GetDataPresenter(this);
        video_list_tiaomu = findViewById(R.id.video_list_tiaomu);
        mVideo_list_title = findViewById(R.id.video_list_title);
        video_list_name = findViewById(R.id.video_list_name);
        mBack = findViewById(R.id.back);
        GridLayoutManager layoutmanager = new GridLayoutManager(this, 3);
        //设置RecyclerView 布局
        video_list_tiaomu.setLayoutManager(layoutmanager);
    }

    @Override
    public void initData() {
        Intent intent = getIntent();
        mUrl = intent.getStringExtra("url");
        mName = intent.getStringExtra("name");
        String[] split = mUrl.split("\\?");
        Log.e("gouzhi", split[0]);
        String[] split1 = split[1].split("\\=");

        Map<String, String> map = new HashMap<>();
        map.put(split1[0], split1[1]);
        getDataPresenter.getDataFromNet("front/videoDetailApi/videoDetail.do", map);

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        video_list_name.setText(mName);
    }

    @Override
    public int setChildContentView() {
        return R.layout.activity_video_list;

    }

    public GetDataPresenter setPresenter() {

        return new GetDataPresenter(this);
    }

    @Override
    public void onSuccess(ResponseBody responseBody) {
        try {
            String string = responseBody.string();
            videoListBean = new Gson().fromJson(string, VideoListBean.class);
            videoListAdapter = new VideoListAdapter(videoListBean, this);
            video_list_tiaomu.setAdapter(videoListAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
        videoListAdapter.setOnItemClickListener(new VideoListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                List<VideoListBean.RetBean.ListBean.ChildListBean> childList = videoListBean.getRet().getList().get(0).getChildList();
                EventBus.getDefault().postSticky(new FirstEvent(childList));
                Intent intent = new Intent(VideoListActivity.this, VideosDetailActivity.class);
                intent.putExtra("url",mUrl);
                intent.putExtra("spurl",videoListBean.getRet().getHDURL());
                startActivity(intent);


            }
        });

    }

    @Override
    public void onError(Throwable throwable) {
        if (BuildConfig.DEBUG) Log.d("VideoListActivity", "throwable:" + throwable);
    }
}
