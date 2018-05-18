package com.lee.myghost.mvp.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.lee.myghost.R;
import com.lee.myghost.api.Constant;
import com.lee.myghost.mvp.model.bean.VideoList_Bean;
import com.lee.myghost.mvp.model.contract.viewinter.GetDataFromNetInter;
import com.lee.myghost.mvp.presenter.GetDataPresenter;
import com.lee.myghost.mvp.view.activities.VideoListActivity;
import com.lee.myghost.mvp.view.adapters.SpeicalFragmentAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * @author Lee
 * @create_time 2018/5/16 19:37
 * @description 专题的Fragment
 */
public class SpeicalFragment extends Fragment implements GetDataFromNetInter {

    private RecyclerView special_tiaomo;
    private GetDataPresenter getDataPresenter;
    private SpeicalFragmentAdapter speicalFragmentAdapter;
    private VideoList_Bean videoList_bean;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_special,container,false);
        special_tiaomo = view.findViewById(R.id.special_tiaomo);
        getDataPresenter = new GetDataPresenter(this);
        GridLayoutManager layoutmanager = new GridLayoutManager(getContext(),2);
        //设置RecyclerView 布局
        special_tiaomo.setLayoutManager(layoutmanager);
        //设置Adapter
        Map<String, String> map=new HashMap<>();
        //catalogId=402834815584e463015584e539330016&pnum=7
        map.put("catalogId","402834815584e463015584e539330016");
        map.put("pnum","7");
        getDataPresenter.getDataFromNet(Constant.VIDEO_CATEGORY_URL,map);
        return view;
    }

    @Override
    public void onSuccess(ResponseBody responseBody) {
        try {
            String string = responseBody.string();
            videoList_bean = new Gson().fromJson(string, VideoList_Bean.class);
            Log.e("wang", videoList_bean.getRet().getAdv()+"wangzi");
            speicalFragmentAdapter = new SpeicalFragmentAdapter(videoList_bean,getContext());
            special_tiaomo.setAdapter(speicalFragmentAdapter);
            speicalFragmentAdapter.setOnItemClickListener(new SpeicalFragmentAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    String loadURL = videoList_bean.getRet().getList().get(position).getLoadURL();
                    Intent intent = new Intent(getActivity(), VideoListActivity.class);
                    intent.putExtra("url",loadURL);
                    startActivity(intent);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable throwable) {

    }
}
