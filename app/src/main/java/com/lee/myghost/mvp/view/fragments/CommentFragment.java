package com.lee.myghost.mvp.view.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lee.myghost.R;
import com.lee.myghost.mvp.view.adapters.CommentAdapter;

/**
 * @author Lee
 * @create_time 2018/5/22 14:03
 * @description 评论的fragment
 */
public class CommentFragment extends Fragment {

    private View         mView;
    private RecyclerView mCommentRecycler;
    private CommentAdapter mCommentAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_comment, container, false);

        initView(mView);
        initData();

        return mView;
    }

    private void initData() {
        mCommentRecycler.setLayoutManager(new LinearLayoutManager(getActivity()));
//        mCommentAdapter = new CommentAdapter(getActivity(),);
//        mCommentRecycler.setAdapter(mCommentAdapter);
    }

    private void initView(View mView) {
        mCommentRecycler = (RecyclerView) mView.findViewById(R.id.comment_recycler);
    }
}
