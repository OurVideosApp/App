package com.lee.myghost.mvp.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.lee.myghost.R;
import com.lee.myghost.mvp.model.bean.VideoComment_Bean;
import com.lee.myghost.mvp.view.holders.CommentHolder;

/**
 * @author Lee
 * @create_time 2018/5/23 9:16
 * @description
 */
public class CommentAdapter extends RecyclerView.Adapter<CommentHolder> {

    private Context           mContext;
    private VideoComment_Bean mBean;

    public CommentAdapter(Context context, VideoComment_Bean bean) {
        mContext = context;
        mBean = bean;
    }

    @NonNull
    @Override
    public CommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mContext, R.layout.adapter_item_comment, null);
        CommentHolder holder = new CommentHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mBean.getRet().getList().size();
    }
}
