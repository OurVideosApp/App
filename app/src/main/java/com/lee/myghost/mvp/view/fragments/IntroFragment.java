package com.lee.myghost.mvp.view.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lee.myghost.R;
import com.ms.square.android.expandabletextview.ExpandableTextView;

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

    private void initView(View view) {

        mIntroDirector = (TextView) view.findViewById(R.id.intro_director);
        mIntroActors = (TextView) view.findViewById(R.id.intro_actors);
        mExpandableText = (TextView) view.findViewById(R.id.expandable_text);
        mExpandCollapse = (ImageButton) view.findViewById(R.id.expand_collapse);
        mExpandTextView = (ExpandableTextView) view.findViewById(R.id.expand_text_view);
        mIntroRecyclerview = (RecyclerView) view.findViewById(R.id.intro_recyclerview);
    }
}
