package com.lee.myghost.mvp.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lee.myghost.R;
import com.lee.myghost.mvp.model.bean.VideoListBean;

import java.util.List;

public class IntroAdapter extends RecyclerView.Adapter<IntroAdapter.introholder>{
    private final Context context;
    private final List<VideoListBean.RetBean.ListBean.ChildListBean> childList;

    public IntroAdapter(Context context, List<VideoListBean.RetBean.ListBean.ChildListBean> childList) {
        this.context=context;
        this.childList=childList;
    }

    @NonNull
    @Override
    public introholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.video_list_tabulate, null);
        introholder introholder = new introholder(inflate);
        return introholder;
    }

    @Override
    public void onBindViewHolder(@NonNull introholder holder, int position) {
        Glide.with(context).load(childList.get(position).getPic()).into(holder.speical_fragment_tabulate_img);
        holder.speical_fragment_tabulate_name.setText(childList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        return childList.size();
    }
    class introholder extends RecyclerView.ViewHolder {
        public ImageView speical_fragment_tabulate_img;
        public TextView speical_fragment_tabulate_name;
        public introholder(View itemView) {
            super(itemView);
            speical_fragment_tabulate_img = itemView.findViewById(R.id.speical_fragment_tabulate_img);
            speical_fragment_tabulate_name = itemView.findViewById(R.id.speical_fragment_tabulate_name);
        }
    }
}
