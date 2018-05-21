package com.lee.myghost.mvp.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.lee.myghost.R;
import com.lee.myghost.api.Constant;
import com.lee.myghost.mvp.model.bean.ChoicenessBean;
import com.lee.myghost.mvp.model.contract.viewinter.GetDataFromNetInter;
import com.lee.myghost.mvp.presenter.GetDataPresenter;
import com.lee.myghost.utils.glide.GlideImageLoader;
import com.youth.banner.Banner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;

/**
 * @author Lee
 * @create_time 2018/5/16 19:37
 * @description 精选的Fragment
 */
public class ChoicenessFragment extends Fragment implements GetDataFromNetInter {

    private LinearLayout     choiceness_linearlayout;
    private View             view;
    private Banner           choiceness_banner;
    private RecyclerView     choiceness_recylerview;
    private EditText         choiceness_edittext;
    private GetDataPresenter getDataPresenter;
    private List<String>     imageurls;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_choiceness, container, false);
        choiceness_linearlayout = view.findViewById(R.id.choiceness_linearlayout);
        choiceness_linearlayout.getBackground().setAlpha(100);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        choiceness_banner = view.findViewById(R.id.choiceness_banner);
        choiceness_recylerview = view.findViewById(R.id.choiceness_recylerview);
        choiceness_edittext = view.findViewById(R.id.choiceness_edittext);
        getDataPresenter = new GetDataPresenter(this);
        Map<String, String> map = new HashMap<>();
        getDataPresenter.getDataFromNet(Constant.HOME_PAGE_URL, map);
    }

    @Override
    public void onSuccess(ResponseBody responseBody) {
        try {
            String json = responseBody.string();
            ChoicenessBean choicenessBean = new Gson().fromJson(json, ChoicenessBean.class);
            ChoicenessBean.RetBean ret = choicenessBean.getRet();
            /*轮播图*/
            List<ChoicenessBean.RetBean.ListBean> list = ret.getList();
            imageurls = new ArrayList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getShowType().equals("banner")) {
                    List<ChoicenessBean.RetBean.ListBean.ChildListBean> childList = list.get(i).getChildList();
                    for (int j = 0; j < childList.size(); j++) {
                        String pic = childList.get(j).getPic().toString();
                        imageurls.add(pic);
                    }
                }
            }
            choiceness_banner.setImageLoader(new GlideImageLoader());
            choiceness_banner.setImages(imageurls);
            choiceness_banner.isAutoPlay(true);
            choiceness_banner.setDelayTime(2000);
            choiceness_banner.start();
            /*精彩推荐*/

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        Log.d("ChoicenessFragment", "throwable:" + throwable);
    }
}
