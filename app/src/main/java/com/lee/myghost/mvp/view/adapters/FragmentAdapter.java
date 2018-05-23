package com.lee.myghost.mvp.view.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @author Lee
 * @create_time 2018/5/22 14:00
 * @description
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragmentList;
    private List<String>   title;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragments, List<String> titles) {
        super(fm);
        fragmentList = fragments;
        title = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }
}