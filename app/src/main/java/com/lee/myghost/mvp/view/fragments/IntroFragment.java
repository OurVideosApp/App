package com.lee.myghost.mvp.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lee.myghost.R;
import com.lee.myghost.mvp.model.bean.VideoListBean;
import com.lee.myghost.mvp.view.adapters.IntroAdapter;
import com.lee.myghost.utils.FirstEvent;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * @author Lee
 * @create_time 2018/5/22 14:02
 * @description 简介的fragment
 */
public class IntroFragment extends Fragment {

    private View               view;
    /**
     * 导演：
     */
    private TextView           mIntroDirector;
    /**
     * 演员：
     */
    private TextView           mIntroActors;
    private TextView           mExpandableText;
    private ImageButton        mExpandCollapse;
    private ExpandableTextView mExpandTextView;
    private RecyclerView       mIntroRecyclerview;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {



        view = inflater.inflate(R.layout.fragment_intro, container, false);
        initView(view);
        initData();
        return view;
    }

    @SuppressLint("ResourceType")
    private void initData() {
        mExpandTextView.setText("测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据测试数据");
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!EventBus.getDefault().isRegistered(this))
        {
            EventBus.getDefault().register(this);
        }
    }

    private void initView(View view) {

        mIntroDirector = (TextView) view.findViewById(R.id.intro_director);
        mIntroActors = (TextView) view.findViewById(R.id.intro_actors);
        mExpandableText = (TextView) view.findViewById(R.id.expandable_text);
        mExpandCollapse = (ImageButton) view.findViewById(R.id.expand_collapse);
        mExpandTextView = (ExpandableTextView) view.findViewById(R.id.expand_text_view);
        mIntroRecyclerview = (RecyclerView) view.findViewById(R.id.intro_recyclerview);
    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onEventMainThread(FirstEvent event) {

        List<VideoListBean.RetBean.ListBean.ChildListBean> childList = event.getChildList();
        VideoListBean.RetBean.ListBean.ChildListBean childListBean = childList.get(0);
        String title = childListBean.getTitle();
        IntroAdapter introAdapter = new IntroAdapter(getActivity(),childList);
        GridLayoutManager layoutmanager = new GridLayoutManager(getActivity(), 2);
        //设置RecyclerView 布局
        mIntroRecyclerview.setLayoutManager(layoutmanager);
        mIntroRecyclerview.setAdapter(introAdapter);
        Log.e("wangzi22", title+"12345678987654321");
//        tv.setText(msg);
//        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus

    }
}
