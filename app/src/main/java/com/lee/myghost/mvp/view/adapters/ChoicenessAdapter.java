package com.lee.myghost.mvp.view.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.lee.myghost.R;
import com.lee.myghost.mvp.model.bean.ChoicenessBean;

public class ChoicenessAdapter extends RecyclerView.Adapter<ChoicenessAdapter.ViewHolder> {
    private ChoicenessBean.RetBean ret;
    private Context context;

    public ChoicenessAdapter(ChoicenessBean.RetBean ret, Context context) {
        this.ret = ret;
        this.context = context;
    }

    @NonNull
    @Override
    public ChoicenessAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.choiceness_item_adapter,null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChoicenessAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return ret.getList().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
