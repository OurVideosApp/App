package com.lee.myghost.mvp.model.contract.viewinter;

/**
 * @author Lee
 * @create_time 2018/5/16 19:56
 * @description 点击的接口
 */
public interface OnItemListner {
    //条目点击的方法
    void onItemClick(int position);

    //条目长按的方法
    void onItemLongClick(int position);
}