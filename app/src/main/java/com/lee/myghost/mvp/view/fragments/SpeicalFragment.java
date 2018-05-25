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
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lee.myghost.R;
import com.lee.myghost.api.Constant;
import com.lee.myghost.mvp.model.bean.ChoicenessBean;
import com.lee.myghost.mvp.model.bean.VideoList_Bean;
import com.lee.myghost.mvp.model.contract.viewinter.GetDataFromNetInter;
import com.lee.myghost.mvp.presenter.GetDataPresenter;
import com.lee.myghost.mvp.view.activities.VideoListActivity;
import com.lee.myghost.mvp.view.adapters.SpeicalFragmentAdapter;
import com.lee.myghost.utils.CommonUtil;

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
    private ChoicenessBean videoList_bean;
    private TextView special_title;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_special,container,false);
        special_tiaomo = view.findViewById(R.id.special_tiaomo);
        special_title = view.findViewById(R.id.special_title);
        getDataPresenter = new GetDataPresenter(this);
        GridLayoutManager layoutmanager = new GridLayoutManager(getContext(),2);
        //设置RecyclerView 布局
        special_tiaomo.setLayoutManager(layoutmanager);
        //设置Adapter
        Map<String, String> map=new HashMap<>();
        getDataPresenter.getDataFromNet(Constant.HOME_PAGE_URL,map);
        return view;
    }

    public void setTitleBackGround(int color){
        special_title.setBackgroundColor(color);
    }


    @Override
    public void onResume() {
        super.onResume();
        int colorValue = CommonUtil.obtainColorValue();
        if(colorValue!=-1){
            special_title.setBackgroundColor(colorValue);
        }

    }

    @Override
    public void onSuccess(ResponseBody responseBody) {
        try {
            String string = responseBody.string();
            videoList_bean = new Gson().fromJson(string, ChoicenessBean.class);
//            Log.e("wang", videoList_bean.getRet().getAdv()+"wangzi");
            speicalFragmentAdapter = new SpeicalFragmentAdapter(videoList_bean,getContext());
            special_tiaomo.setAdapter(speicalFragmentAdapter);
            speicalFragmentAdapter.setOnItemClickListener(new SpeicalFragmentAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(View view, int position) {
                    String loadURL = videoList_bean.getRet().getList().get(position).getChildList().get(0).getLoadURL();
                    if (!loadURL.startsWith("http://api.svipmovie.com/")||position==2){
                        Toast.makeText(getContext(),"没有电影",Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent = new Intent(getActivity(), VideoListActivity.class);
                        intent.putExtra("url",loadURL);
                        intent.putExtra("name",videoList_bean.getRet().getList().get(position).getTitle());
                        startActivity(intent);
                    }

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
