package com.lee.myghost.mvp.view.fragments;

import android.content.Intent;
import android.graphics.Color;
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
import android.view.ViewTreeObserver;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lee.myghost.R;
import com.lee.myghost.api.Constant;
import com.lee.myghost.mvp.model.bean.ChoicenessBean;
import com.lee.myghost.mvp.model.contract.viewinter.GetDataFromNetInter;
import com.lee.myghost.mvp.presenter.GetDataPresenter;
import com.lee.myghost.mvp.view.activities.SearchActivity;
import com.lee.myghost.mvp.view.activities.VideoListActivity;
import com.lee.myghost.mvp.view.activities.VideosDetailActivity;
import com.lee.myghost.mvp.view.adapters.ChoicenessAdapter;
import com.lee.myghost.mvp.view.customviews.ObservableScrollView;
import com.lee.myghost.utils.ChenJinUtil;
import com.lee.myghost.utils.CommonUtil;
import com.lee.myghost.utils.glide.GlideImageLoader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
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
    private TextView choiceness_edittext;
    private GetDataPresenter getDataPresenter;
    private List<String> imageurls;
    private List<ChoicenessBean.RetBean.ListBean.ChildListBean> childLists;
    private RelativeLayout choiceness_title;
    private ObservableScrollView observe_scroll_view;
    private int height;
    private final String TAG_MARGIN_ADDED = "marginAdded";
    private SmartRefreshLayout choiceness_smartrefresh;
    private TextView choiceness_title_name;
    private int imageHeight=500;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_choiceness, container, false);
        choiceness_linearlayout = view.findViewById(R.id.choiceness_linearlayout);
        observe_scroll_view = view.findViewById(R.id.observe_scroll_view);
        choiceness_smartrefresh = view.findViewById(R.id.choiceness_smartrefresh);
        choiceness_title = view.findViewById(R.id.choiceness_title);
        choiceness_title_name = view.findViewById(R.id.choiceness_title_name);
        ChenJinUtil.setStatusBarColor(getActivity(),Color.TRANSPARENT);
        initTitle();
        smartrefresh();
        return view;
    }

    private void smartrefresh() {
        choiceness_smartrefresh.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                    choiceness_smartrefresh.finishRefresh(2000);
            }
        });
    }

    private void initTitle() {
        choiceness_title.bringToFront();
        observe_scroll_view.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y <= 0) {
                    choiceness_title.setBackgroundColor(Color.argb((int) 0, 2, 255, 37));
                    choiceness_title_name.setTextColor(Color.argb((int) 0, 255, 255, 255));
                    choiceness_title_name.setText("精选");
                    //AGB由相关工具获得，或者美工提供
                } else if (y > 0 && y <= imageHeight) {
                    float scale = (float) y / imageHeight;
                    float alpha = (255 * scale);
// 只是layout背景透明
                    choiceness_title.setBackgroundColor(Color.argb((int) alpha, 2, 255, 37));
                    choiceness_title_name.setTextColor(Color.argb((int) alpha, 255, 255, 255));
                } else {
                    choiceness_title.setBackgroundColor(Color.argb((int) 255, 2, 255, 37));
                    choiceness_title_name.setTextColor(Color.argb((int) 255, 255, 255, 255));
                    choiceness_title_name.setText("精选");
                }
            }
        });
    }







    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        choiceness_linearlayout = view.findViewById(R.id.choiceness_linearlayout);
        choiceness_banner = view.findViewById(R.id.choiceness_banner);
        choiceness_recylerview = view.findViewById(R.id.choiceness_recylerview);
        choiceness_edittext = view.findViewById(R.id.choiceness_edittext);
        choiceness_title = view.findViewById(R.id.choiceness_title);

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
            final ChoicenessBean.RetBean ret = choicenessBean.getRet();
            List<ChoicenessBean.RetBean.ListBean> list = ret.getList();
            /*轮播图*/
            imageurls = new ArrayList<>();
            for (int i=0; i<list.size(); i++){
                if (list.get(i).getShowType().equals("banner")){
                    List<ChoicenessBean.RetBean.ListBean.ChildListBean> childList = list.get(i).getChildList();
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
            for (int i=0; i<list.size(); i++){
                if (list.get(i).getShowType().equals("IN")){
                    childLists = list.get(i).getChildList();

                }
            }
            ChoicenessAdapter choicenessAdapter = new ChoicenessAdapter(childLists,getActivity());
            choiceness_recylerview.setAdapter(choicenessAdapter);
            choicenessAdapter.setmListener(new ChoicenessAdapter.ItemClickListener() {
                @Override
                public void OnItemClick(int position) {
                    String loadURL = childLists.get(position).getLoadURL();
                    if (!loadURL.startsWith("http://api.svipmovie.com/")||position==2){
                        Toast.makeText(getContext(),"没有电影",Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent = new Intent(getActivity(), VideosDetailActivity.class);
                        intent.putExtra("url",loadURL);
                        intent.putExtra("spurl",childLists.get(position).getShareURL());
                        intent.putExtra("title",childLists.get(position).getTitle());
                        startActivity(intent);;
                    }
                }
            });
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
