//package com.lee.myghost.mvp.view.activities;
//
//import android.os.Bundle;
//import android.util.Log;
//
//import com.lee.myghost.BuildConfig;
//import com.lee.myghost.R;
//import com.lee.myghost.mvp.model.base.BaseAvtivity;
//import com.lee.myghost.mvp.model.contract.viewinter.GetDataFromNetInter;
//import com.lee.myghost.mvp.presenter.GetDataPresenter;
//import com.sackcentury.shinebuttonlib.ShineButton;
//
//import okhttp3.ResponseBody;
//
//public class VideosDetailActivity extends BaseAvtivity<GetDataPresenter> implements GetDataFromNetInter {
//
//    private ShineButton mVideos_detail_collect_button;
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//    }
//
//    @Override
//    public void initView() {
//        mVideos_detail_collect_button = findViewById(R.id.videos_detail_collect_button);
//        mVideos_detail_collect_button.init(this);
//    }
//
//    @Override
//    public void initData() {
//
//    }
//
//    @Override
//    public int setChildContentView() {
//        return R.layout.activity_videos_detail;
//    }
//
//    @Override
//    public GetDataPresenter setPresenter() {
//        return new GetDataPresenter(this);
//    }
//
//    @Override
//    public void onSuccess(ResponseBody responseBody) {
//
//    }
//
//    @Override
//    public void onError(Throwable throwable) {
//        if (BuildConfig.DEBUG) Log.d("VideosDetailActivity", "throwable:" + throwable);
//    }
//}
