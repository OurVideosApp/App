package com.lee.myghost.mvp.view.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.view.SimpleDraweeView;
import com.lee.myghost.R;
import com.lee.myghost.mvp.model.bean.Welfarebean;
import com.lee.myghost.mvp.view.activities.WelfareActivity;

import retrofit2.http.Url;

public class WelfareAdapter extends RecyclerView.Adapter<WelfareAdapter.Holder>{
    private final Context context;
    private final Welfarebean welfarebean;

    public WelfareAdapter(Context context, Welfarebean welfarebean) {
        this.context=context;
        this.welfarebean=welfarebean;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.welfare_item, null);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Glide.with(context).load(welfarebean.getResults().get(position).getUrl()).into(holder.weifare_img);
        Log.e("wang", welfarebean.getResults().get(0).getUrl());
//
//        Uri uri = Uri.parse(welfarebean.getResults().get(position).getUrl());
//        holder.weifare_img.setImageURI(uri);
    }

    @Override
    public int getItemCount() {
        return welfarebean.getResults().size();
    }
    class Holder extends RecyclerView.ViewHolder {

        public ImageView weifare_img;

        public Holder(View itemView) {
            super(itemView);
            weifare_img = itemView.findViewById(R.id.weifare_img);
        }
    }
}
