package com.lee.myghost.mvp.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
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
import com.lee.myghost.mvp.view.activities.SearchActivity;
import com.lee.myghost.mvp.view.adapters.ChoicenessAdapter;
import com.lee.myghost.utils.glide.GlideImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

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
public class ChoicenessFragment extends Fragment implements GetDataFromNetInter, View.OnClickListener {

    private LinearLayout choiceness_linearlayout;
    private View view;
    private Banner choiceness_banner;
    private RecyclerView choiceness_recylerview;
    private EditText choiceness_edittext;
    private GetDataPresenter getDataPresenter;
    private List<String> imageurls;
    private List<ChoicenessBean.RetBean.ListBean.ChildListBean> childList;

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
        choiceness_linearlayout = view.findViewById(R.id.choiceness_linearlayout);
        choiceness_banner = view.findViewById(R.id.choiceness_banner);
        choiceness_recylerview = view.findViewById(R.id.choiceness_recylerview);
        choiceness_edittext = view.findViewById(R.id.choiceness_edittext);
        /*解决滑动卡顿*/
        choiceness_recylerview.setHasFixedSize(true);
        choiceness_recylerview.setNestedScrollingEnabled(false);
        /*搜索框透明*/
        choiceness_linearlayout.getBackground().setAlpha(100);
        /*获取数据*/
        getDataPresenter = new GetDataPresenter(this);
        Map<String, String> map=new HashMap<>();
        getDataPresenter.getDataFromNet(Constant.HOME_PAGE_URL,map);
        /*recyclerview设置布局*/
        choiceness_recylerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        /*点击搜索*/
        choiceness_edittext.setOnClickListener(this);
    }

    @Override
    public void onSuccess(ResponseBody responseBody) {
        try {
            String json = responseBody.string();
            ChoicenessBean choicenessBean = new Gson().fromJson(json, ChoicenessBean.class);
            ChoicenessBean.RetBean ret = choicenessBean.getRet();
            List<ChoicenessBean.RetBean.ListBean> list = ret.getList();
            /*轮播图*/
            imageurls = new ArrayList<>();
            for (int i=0; i<list.size(); i++){
                if (list.get(i).getShowType().equals("banner")){
                    childList = list.get(i).getChildList();
                    for (int j = 0; j< childList.size(); j++){
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

            ChoicenessAdapter choicenessAdapter = new ChoicenessAdapter(childList,getActivity());
            choiceness_recylerview.setAdapter(choicenessAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable throwable) {
        Log.d("ChoicenessFragment", "throwable:" + throwable);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.choiceness_edittext:
                Intent intent = new Intent(getActivity(),SearchActivity.class);
                startActivity(intent);
                break;
        }
    }
}
