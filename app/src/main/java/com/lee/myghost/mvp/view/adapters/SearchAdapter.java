package com.lee.myghost.mvp.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lee.myghost.R;
import com.lee.myghost.mvp.model.bean.ChoicenessBean;
import com.lee.myghost.mvp.model.bean.SearchBean;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private List<SearchBean.RetBean.ListBean> list;
    private Context context;
    private ItemClickListener mListener;

    public void setmListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    public SearchAdapter(List<SearchBean.RetBean.ListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.choiceness_item_adapter,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, final int position) {
        String pic = list.get(position).getPic();
        Glide.with(context).load(pic).into(holder.choicenessadapter_imageview);
            holder.choicenessadapter_linerlayout.getBackground().setAlpha(100);
            holder.choicenessadapter_name.setText(list.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.OnItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView choicenessadapter_name;
        private  ImageView choicenessadapter_imageview;
        private RelativeLayout choicenessadapter_linerlayout;

        public ViewHolder(View itemView) {
            super(itemView);
            choicenessadapter_linerlayout = itemView.findViewById(R.id.choicenessadapter_linerlayout);
            choicenessadapter_imageview = itemView.findViewById(R.id.choicenessadapter_imageview);
            choicenessadapter_name = itemView.findViewById(R.id.choicenessadapter_name);
        }
    }


    public interface ItemClickListener {
        void OnItemClick(int position);
    }
}
