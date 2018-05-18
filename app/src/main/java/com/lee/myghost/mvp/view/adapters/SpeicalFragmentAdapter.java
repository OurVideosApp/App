package com.lee.myghost.mvp.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lee.myghost.R;
import com.lee.myghost.mvp.model.bean.VideoList_Bean;

public class SpeicalFragmentAdapter extends RecyclerView.Adapter<SpeicalFragmentAdapter.holder>{

    private final VideoList_Bean videoList_bean;
    private final Context context;

    public SpeicalFragmentAdapter(VideoList_Bean videoList_bean, Context context) {
        this.videoList_bean=videoList_bean;
        this.context=context;
    }
    private OnItemClickListener mOnItemClickListener = null;


    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.speical_fragment_tabulate, null);
        holder holder = new holder(inflate);
        inflate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    //注意这里使用getTag方法获取position
                    Log.e("wangzi", "onClick: " + v.toString() + "++++++++++");
                    int position = (int) v.getTag();
                    Log.e("wangzi", "onClick: " + position + "++++++++++");
                    mOnItemClickListener.onItemClick(v, position);
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull holder holder, int position) {
        holder.itemView.setTag(position);
        Glide.with(context).load(videoList_bean.getRet().getList().get(position).getPic()).into(holder.speical_fragment_tabulate_img);
        holder.speical_fragment_tabulate_name.setText(videoList_bean.getRet().getList().get(position).getTitle());
        holder.speical_fragment_tabulate_name.getBackground().setAlpha(80);
        holder.speical_fragment_tabulate_background.getBackground().setAlpha(80);
    }

    @Override
    public int getItemCount() {
        return videoList_bean.getRet().getList().size();
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    class holder extends RecyclerView.ViewHolder {

        public ImageView speical_fragment_tabulate_img;
        public TextView speical_fragment_tabulate_name;
        public RelativeLayout speical_fragment_tabulate_background;

        public holder(View itemView) {
            super(itemView);
            speical_fragment_tabulate_img = itemView.findViewById(R.id.speical_fragment_tabulate_img);
            speical_fragment_tabulate_name = itemView.findViewById(R.id.speical_fragment_tabulate_name);
            speical_fragment_tabulate_background = itemView.findViewById(R.id.speical_fragment_tabulate_background);
    }
    }
}
