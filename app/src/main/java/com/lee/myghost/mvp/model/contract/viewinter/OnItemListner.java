package com.lee.myghost.mvp.model.contract.viewinter;

public interface OnItemListner {
    //条目点击的方法
    void onItemClick(int position);

    //条目长按的方法
    void onItemLongClick(int position);
}