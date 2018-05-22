package com.lee.myghost.mvp.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.lee.myghost.R;

/**
 * @author Lee
 * @create_time 2018/5/16 20:52
 * @description 我的工具箱
 */
public class MineFragment extends Fragment implements View.OnClickListener{

    private View view;
    private ImageView mine_img_shezhi;
    private RelativeLayout mine_lishi;
    private LinearLayout mine_lishi_vadio1;
    private LinearLayout mine_lishi_vadio2;
    private LinearLayout mine_lishi_vadio3;
    private RelativeLayout mine_huancun;
    private RelativeLayout mine_shoucang;
    private RelativeLayout mine_zhuti;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mine,container,false);

        initview();

        return view;
    }

    //初始化组件
    private void initview() {

        //设置
        mine_img_shezhi = (ImageView) view.findViewById(R.id.mine_img_shezhi);

        //历史
        mine_lishi = (RelativeLayout) view.findViewById(R.id.mine_lishi);

        //历史下显示
        mine_lishi_vadio1 = (LinearLayout) view.findViewById(R.id.mine_lishi_vadio1);
        mine_lishi_vadio2 = (LinearLayout) view.findViewById(R.id.mine_lishi_vadio2);
        mine_lishi_vadio3 = (LinearLayout) view.findViewById(R.id.mine_lishi_vadio3);

        //缓存
        mine_huancun = (RelativeLayout) view.findViewById(R.id.mine_huancun);
        //收藏
        mine_shoucang = (RelativeLayout) view.findViewById(R.id.mine_shoucang);
        //主题
        mine_zhuti = (RelativeLayout) view.findViewById(R.id.mine_zhuti);

        mine_img_shezhi.setOnClickListener(this);
        mine_lishi.setOnClickListener(this);
        mine_lishi_vadio1.setOnClickListener(this);
        mine_lishi_vadio2.setOnClickListener(this);
        mine_lishi_vadio3.setOnClickListener(this);
        mine_huancun.setOnClickListener(this);
        mine_shoucang.setOnClickListener(this);
        mine_zhuti.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //设置
            case R.id.mine_img_shezhi:
                Log.d("我的页面","设置");
                break;

            //历史
            case R.id.mine_lishi:
                Log.d("我的页面","历史");
                break;

                /*历史下的三个vsdio*/
            case R.id.mine_lishi_vadio1:
                Log.d("我的页面","历史1");
                break;

            case R.id.mine_lishi_vadio2:
                Log.d("我的页面","历史2");
                break;

            case R.id.mine_lishi_vadio3:
                Log.d("我的页面","历史3");
                break;

            //缓存
            case R.id.mine_huancun:
                Log.d("我的页面","缓存");
                Toast.makeText(getContext(),"微影：敬请期待",Toast.LENGTH_SHORT).show();
                break;

            //收藏
            case R.id.mine_shoucang:
                Log.d("我的页面","收藏");
             /*   Intent intent=new Intent(getContext(),Mine_ShouCang.class);
                startActivity(intent);*/
                break;

            //主题
            case R.id.mine_zhuti:
                Log.d("我的页面","主题");
                break;
        }
    }
}
