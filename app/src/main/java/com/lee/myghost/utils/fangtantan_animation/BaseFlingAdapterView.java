package com.lee.myghost.utils.fangtantan_animation;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AdapterView;
/*
* 发现页面 防探探卡片动画的 Utils
* 2018-05-21 15:13
* 王彦勇
*
* */
abstract class BaseFlingAdapterView extends AdapterView {
    private int heightMeasureSpec;
    private int widthMeasureSpec;
    public BaseFlingAdapterView(Context context) {
        super(context);
    }
    public BaseFlingAdapterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public BaseFlingAdapterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public void setSelection(int i) {
        throw new UnsupportedOperationException("Not supported");
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.widthMeasureSpec = widthMeasureSpec;
        this.heightMeasureSpec = heightMeasureSpec;
    }
    public int getWidthMeasureSpec() {
        return widthMeasureSpec;
    }
    public int getHeightMeasureSpec() {
        return heightMeasureSpec;
    }
}
