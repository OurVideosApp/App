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
import com.lee.myghost.mvp.model.bean.VideoListBean;
import com.lee.myghost.mvp.model.bean.VideoList_Bean;

public class VideoListAdapter  extends RecyclerView.Adapter<VideoListAdapter.holder>{

    private final VideoListBean videoListBean;
    private final Context context;

    public VideoListAdapter(VideoListBean videoListBean, Context context) {
        this.videoListBean=videoListBean;
        this.context=context;
    }
    private OnItemClickListener mOnItemClickListener = null;


    public static interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = View.inflate(context, R.layout.video_list_tabulate, null);
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
        Glide.with(context).load(videoListBean.getRet().getList().get(0).getChildList().get(position).getPic()).into(holder.speical_fragment_tabulate_img);
        holder.speical_fragment_tabulate_name.setText(videoListBean.getRet().getList().get(0).getChildList().get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return videoListBean.getRet().getList().get(0).getChildList().size();
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }
    class holder extends RecyclerView.ViewHolder {

        public ImageView speical_fragment_tabulate_img;
        public TextView speical_fragment_tabulate_name;

        public holder(View itemView) {
            super(itemView);
            speical_fragment_tabulate_img = itemView.findViewById(R.id.speical_fragment_tabulate_img);
            speical_fragment_tabulate_name = itemView.findViewById(R.id.speical_fragment_tabulate_name);
        }
    }
}
