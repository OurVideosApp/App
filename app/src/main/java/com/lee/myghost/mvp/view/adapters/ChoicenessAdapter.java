package com.lee.myghost.mvp.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.drawee.gestures.GestureDetector;
import com.lee.myghost.R;
import com.lee.myghost.mvp.model.bean.ChoicenessBean;
import com.lee.myghost.utils.glide.GlideImageLoader;

import java.util.List;

public class ChoicenessAdapter extends RecyclerView.Adapter<ChoicenessAdapter.ViewHolder> {
    private List<ChoicenessBean.RetBean.ListBean.ChildListBean> childLists;
    private Context                                             context;
    private ItemClickListener                                   mListener;

    public void setmListener(ItemClickListener mListener) {
        this.mListener = mListener;
    }

    public ChoicenessAdapter(List<ChoicenessBean.RetBean.ListBean.ChildListBean> childLists, Context context) {
        this.childLists = childLists;
        this.context = context;
    }

    @NonNull
    @Override
    public ChoicenessAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.choiceness_item_adapter, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChoicenessAdapter.ViewHolder holder, final int position) {
                String pic = childLists.get(position).getPic().toString();
                Glide.with(context).load(pic).into(holder.choicenessadapter_imageview);
                holder.choicenessadapter_name.setText(childLists.get(position).getTitle());
                holder.choicenessadapter_linerlayout.getBackground().setAlpha(100);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.OnItemClick(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return childLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView       choicenessadapter_name;
        private ImageView      choicenessadapter_imageview;
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
