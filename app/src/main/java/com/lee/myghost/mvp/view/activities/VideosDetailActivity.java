package com.lee.myghost.mvp.view.activities;

import java.lang.reflect.Field;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dl7.player.media.IjkPlayerView;
import com.lee.myghost.BuildConfig;
import com.lee.myghost.R;
import com.lee.myghost.mvp.model.base.BaseAvtivity;
import com.lee.myghost.mvp.model.contract.viewinter.GetDataFromNetInter;
import com.lee.myghost.mvp.presenter.GetDataPresenter;
import com.lee.myghost.mvp.view.adapters.FragmentAdapter;
import com.lee.myghost.mvp.view.fragments.CommentFragment;
import com.lee.myghost.mvp.view.fragments.IntroFragment;
import com.lee.myghost.utils.CommonUtil;
import com.sackcentury.shinebuttonlib.ShineButton;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class VideosDetailActivity extends BaseAvtivity<GetDataPresenter> implements GetDataFromNetInter {
    LinearLayout llTab = null;
    private ImageView      mFinishThisPage;
    private TextView       mTitleText;
    private ShineButton    mVideosDetailCollectButton;
    private RelativeLayout mVideosDetailHeader;
    private TabLayout      mVideosDetailTablayout;
    private ViewPager      mVideosDetailViewpager;
    //标题列表
    ArrayList<String> titleList = new ArrayList<String>();
    //构造适配器
    List<Fragment>    fragments = new ArrayList<Fragment>();
    private IjkPlayerView mPlayerView;
    private Uri mUri;
    private String title;

    //    http://movie.vods00.cnlive.com/video/data1/2017/0614/221621/1500/f7979239681941a89fab4ab5d0650482_221621_1500.m3u8
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        String spurl = intent.getStringExtra("spurl");
        title = intent.getStringExtra("title");

        Toast.makeText(this,url,Toast.LENGTH_SHORT).show();
        mPlayerView = (IjkPlayerView) findViewById(R.id.video_detail_ijkplayer);
        mUri = Uri.parse(spurl);

        mPlayerView.init()
                .setVideoPath(mUri)
                .setMediaQuality(IjkPlayerView.MEDIA_QUALITY_HIGH)
                .enableDanmaku()
                .start();

        initTitle();
    }

    private void initTitle() {

        mTitleText.setText(title);

    }


    @Override
    public void onStart() {
        super.onStart();
        mVideosDetailTablayout.post(new Runnable() {
            @Override
            public void run() {
                setIndicator(mVideosDetailTablayout, 30, 30);
            }
        });
    }

    @Override
    public void initView() {
        mVideosDetailCollectButton = (ShineButton) findViewById(R.id.videos_detail_collect_button);
        mFinishThisPage = (ImageView) findViewById(R.id.finish_this_page);
        mTitleText = (TextView) findViewById(R.id.title_text);
        mVideosDetailHeader = (RelativeLayout) findViewById(R.id.videos_detail_header);
        mVideosDetailTablayout = (TabLayout) findViewById(R.id.videos_detail_tablayout);
        mVideosDetailViewpager = (ViewPager) findViewById(R.id.videos_detail_viewpager);
    }

    @Override
    public void initData() {
        mVideosDetailCollectButton.init(this);
        fragments.add(new IntroFragment());
        fragments.add(new CommentFragment());
        titleList.add("简介");
        titleList.add("评论");
        FragmentAdapter adapter = new FragmentAdapter(this.getSupportFragmentManager(), fragments, titleList);
        mVideosDetailViewpager.setAdapter(adapter);
        mVideosDetailTablayout.setTabMode(TabLayout.MODE_FIXED);
        mVideosDetailTablayout.setupWithViewPager(mVideosDetailViewpager);
//        int selectedTabPosition = mVideosDetailTablayout.getSelectedTabPosition();


//        mVideosDetailTablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
////                tab.
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
    }

    @Override
    public int setChildContentView() {
        return R.layout.activity_videos_detail;
    }

    @Override
    public GetDataPresenter setPresenter() {
        return new GetDataPresenter(this);
    }

    @Override
    public void onSuccess(ResponseBody responseBody) {

    }

    @Override
    public void onError(Throwable throwable) {
        if (BuildConfig.DEBUG) Log.d("VideosDetailActivity", "throwable:" + throwable);
    }

    //设置 tablelayout宽度
    public void setIndicator(TabLayout tabs, int leftDip, int rightDip) {
        Class<?> tabLayout = tabs.getClass();
        Field tabStrip = null;
        try {
            tabStrip = tabLayout.getDeclaredField("mTabStrip");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

        tabStrip.setAccessible(true);

        try {
            llTab = (LinearLayout) tabStrip.get(tabs);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        int left = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, leftDip, Resources.getSystem().getDisplayMetrics());
        int right = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, rightDip, Resources.getSystem().getDisplayMetrics());

        for (int i = 0; i < llTab.getChildCount(); i++) {
            View child = llTab.getChildAt(i);
            child.setPadding(0, 0, 0, 0);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT, 1);
            params.leftMargin = left;
            params.rightMargin = right;
            child.setLayoutParams(params);
            child.invalidate();
        }

    }
    @Override
    protected void onResume() {
        super.onResume();
        mPlayerView.onResume();
        int colorValue = CommonUtil.obtainColorValue();
        if (colorValue!=-1){
            mTitleText.setBackgroundColor(colorValue);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPlayerView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlayerView.onDestroy();

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mPlayerView.configurationChanged(newConfig);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (mPlayerView.handleVolumeKey(keyCode)) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (mPlayerView.onBackPressed()) {
            return;
        }
        super.onBackPressed();
    }
}
