package com.lee.myghost.utils.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.loader.ImageLoader;

/**
 * @author Lee
 * @create_time 2018/5/11 15:39
 * @description Glide简单加载图片类
 */

public class GlideImageLoader extends ImageLoader {
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {

        imageView.setScaleType(ImageView.ScaleType.FIT_XY);

        //Glide 加载图片简单用法
        Glide.with(context).load(path).into(imageView);

    }
}
