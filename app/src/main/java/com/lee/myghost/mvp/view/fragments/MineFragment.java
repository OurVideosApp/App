package com.lee.myghost.mvp.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lee.myghost.R;
import com.lee.myghost.mvp.view.activities.VideosDetailActivity;

/**
 * @author Lee
 * @create_time 2018/5/16 20:52
 * @description 我的工具箱
 */
public class MineFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_mine,container,false);
        Button button = view.findViewById(R.id.clickthis);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(), VideosDetailActivity.class);
                startActivity(intent);

            }
        });
        return view;
    }


}
