package com.lee.myghost.mvp.view.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.lee.myghost.R;
import com.lee.myghost.mvp.view.customviews.CircleImageView;

import java.util.List;


/**
 * author: 晨光光
 * date : 2018/5/22 16:14
 */
public class ThemeAdapter extends BaseAdapter {
    private List<Integer> colorData;
    private int i;
    private Context context;

    public ThemeAdapter(List<Integer> colorData, int i, Context context) {
        this.colorData = colorData;
        this.i = i;
        this.context = context;
    }

    @Override
    public int getCount() {
        return colorData.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = View.inflate(context, R.layout.theme_item_view, null);
        Integer colorAddr = colorData.get(position);
        CircleImageView circleImageView = convertView.findViewById(R.id.theme_item);
        circleImageView.setImageResource(colorAddr);
        if (i == position) {
            circleImageView.setBackgroundResource(R.drawable.theme_color_yellow);
        } else {
            circleImageView.setBackground(null);
        }
        return convertView;
    }

    public void setPosition(int i) {
        this.i = i;
    }
}
