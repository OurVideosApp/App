package com.lee.myghost.mvp.view.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lee.myghost.R;
import com.lee.myghost.SearchDao;
import com.lee.myghost.api.Constant;
import com.lee.myghost.application.MyApplication;
import com.lee.myghost.mvp.model.base.BaseAvtivity;
import com.lee.myghost.mvp.model.bean.ChoicenessBean;
import com.lee.myghost.mvp.model.bean.SearchBean;
import com.lee.myghost.mvp.model.contract.viewinter.GetDataFromNetInter;
import com.lee.myghost.mvp.presenter.GetDataPresenter;
import com.lee.myghost.mvp.view.adapters.ChoicenessAdapter;
import com.lee.myghost.mvp.view.adapters.SearchAdapter;
import com.lee.myghost.mvp.view.customviews.WordWrapView;
import com.lee.myghost.mvp.view.db.Search;
import com.lee.myghost.utils.CommonUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;

public class SearchActivity extends BaseAvtivity<GetDataPresenter> implements View.OnClickListener, GetDataFromNetInter {

    private RecyclerView search_recyclerview;
    private EditText search_edittext;
    private TextView search_textview;
    private ImageView search_clean;
    private LinearLayout search_linearlayout;
    private WordWrapView search_wordwrapview;
    private SearchDao searchDao;
    private ImageView search_clean_edittext;
    private GetDataPresenter getDataPresenter;
    private RelativeLayout search_history_relativelayout;
    private RecyclerView search_listings_recyclerview;
    private List<ChoicenessBean.RetBean.ListBean.ChildListBean> childLists;
    private RelativeLayout search_relativelayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchDao = MyApplication.getInstance().getDaoSession().getSearchDao();
        search_linearlayout.getBackground().setAlpha(100);
        setHistory();

        search_edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if (s.length()>0){
                        search_clean_edittext.setVisibility(View.VISIBLE);
                        search_textview.setText("搜索");
                    }else {
                        search_clean_edittext.setVisibility(View.INVISIBLE);
                        search_textview.setText("取消");
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        search_listings_recyclerview.setLayoutManager(new GridLayoutManager(this,3));
        search_recyclerview.setLayoutManager(new GridLayoutManager(this,2));
    }

    private void setHistory() {
        List<Search> searches = searchDao.loadAll();
        search_wordwrapview.removeAllViews();
        for (int i=0; i<searches.size(); i++){
            String name1 = searches.get(i).getName();
            TextView textView = new TextView(this);
            textView.setText(name1);
            textView.setTextColor(Color.parseColor("#ffffff"));
            search_wordwrapview.addView(textView);
        }
    }

    @Override
    public void initView() {
        search_recyclerview = findViewById(R.id.search_recyclerview);
        search_relativelayout = findViewById(R.id.search_relativelayout);
        search_edittext = findViewById(R.id.search_edittext);
        search_textview = findViewById(R.id.search_textview);
        search_clean = findViewById(R.id.search_clean);
        search_linearlayout = findViewById(R.id.search_linearlayout);
        search_wordwrapview = findViewById(R.id.search_wordwrapview);
        search_clean_edittext = findViewById(R.id.search_clean_edittext);
        search_history_relativelayout = findViewById(R.id.search_history_relativelayout);
        search_listings_recyclerview = findViewById(R.id.search_listings_recyclerview);
        search_textview.setOnClickListener(this);
        search_clean.setOnClickListener(this);
        search_clean_edittext.setOnClickListener(this);
    }



    @Override
    protected void onResume() {
        super.onResume();
        int colorValue = CommonUtil.obtainColorValue();
        if (colorValue!=-1){
            search_relativelayout.setBackgroundColor(colorValue);
        }
    }

    @Override
    public void initData() {
         getDataPresenter = new GetDataPresenter(this);
        Map<String, String> map=new HashMap<>();
        getDataPresenter.getDataFromNet(Constant.HOME_PAGE_URL,map);
    }

    @Override
    public int setChildContentView() {
        return R.layout.activity_search;
    }

    @Override
    public GetDataPresenter setPresenter() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.search_textview:
                 String name = search_edittext.getText().toString();
                 if (!TextUtils.isEmpty(name)){
                     Search search = new Search(System.currentTimeMillis(), name);
                     searchDao.insert(search);
                     Map<String, String> map=new HashMap<>();
                     map.put("keyword",name);
                     getDataPresenter.getDataFromNet(Constant.SEARCH_VIDEO_URL,map);
                     search_history_relativelayout.setVisibility(View.GONE);
                     search_listings_recyclerview.setVisibility(View.VISIBLE);
                 }else {
                     if (this instanceof  SearchActivity){
                         ((SearchActivity)this).finish();
                     }
                 }
                break;
            case R.id.search_clean:
                searchDao.deleteAll();
                search_wordwrapview.removeAllViews();
                break;
            case R.id.search_clean_edittext:
                search_edittext.setText("");
                break;
        }
    }

    @Override
    public void onSuccess(ResponseBody responseBody) {
        try {
            String json = responseBody.string();
            /*搜索列表数据*/
            SearchBean searchBean = new Gson().fromJson(json, SearchBean.class);
            List<SearchBean.RetBean.ListBean> list = searchBean.getRet().getList();
            SearchAdapter searchAdapter = new SearchAdapter(list, SearchActivity.this);
            search_listings_recyclerview.setAdapter(searchAdapter);
            searchAdapter.setmListener(new SearchAdapter.ItemClickListener() {
                @Override
                public void OnItemClick(int position) {

                }
            });
            /*热门推荐*/
            ChoicenessBean choicenessBean = new Gson().fromJson(json, ChoicenessBean.class);
            List<ChoicenessBean.RetBean.ListBean> list1 = choicenessBean.getRet().getList();
            for (int i=0; i<list1.size(); i++){
                childLists = list1.get(i).getChildList();

            }
            ChoicenessAdapter choicenessAdapter = new ChoicenessAdapter(childLists,this);
            search_recyclerview.setAdapter(choicenessAdapter);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onError(Throwable throwable) {

    }
}
