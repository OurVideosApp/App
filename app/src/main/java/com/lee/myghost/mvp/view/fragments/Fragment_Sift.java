package com.lee.myghost.mvp.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lee.myghost.R;


public class Fragment_Sift extends Fragment {

    private LinearLayout sift_linearlayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_jingxuan, container, false);
        sift_linearlayout = view.findViewById(R.id.sift_linearlayout);
        sift_linearlayout.getBackground().setAlpha(100);
        return view;
    }
}
