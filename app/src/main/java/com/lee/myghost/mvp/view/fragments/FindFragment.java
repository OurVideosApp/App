package com.lee.myghost.mvp.view.fragments;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lee.myghost.R;
import com.lee.myghost.utils.fangtantan_animation.SwipeFlingAdapterView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * @author Lee
 * @create_time 2018/5/16 19:37
 * @description 发现的Fragment
 */

public class FindFragment extends Fragment implements SwipeFlingAdapterView.onFlingListener,
        SwipeFlingAdapterView.OnItemClickListener{

    int [] headerIcons = {
            R.mipmap.find_car1_img1,
            R.mipmap.find_car1_img2,
            R.mipmap.find_car1_img3,
            R.mipmap.find_car1_img4,
            R.mipmap.find_car1_img5,
    };

    String [] names = {"张三","李四","王五","小明","小红","小花"};
    String [] citys = {"北京", "上海", "广州", "深圳"};
    Random ran = new Random();
    private int cardWidth;
    private int cardHeight;
    private SwipeFlingAdapterView swipeView;
    private InnerAdapter adapter;
    private View view;
    private Toolbar toolbar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_find,container,false);

   //     toolbar();
        initView();
        loadData();

        return view;
    }

    //设置标题
    private void toolbar() {
        toolbar = (Toolbar) view.findViewById(R.id.find_toolbar);
     //   toolbar.setTitle("发现");
     //   toolbar.setSubtitle("SubTitle");
     //   toolbar.setLogo(R.mipmap.ic_launcher);
    }

    private void initView() {
        DisplayMetrics dm = getResources().getDisplayMetrics();
        float density = dm.density;
        cardWidth = (int) (dm.widthPixels - (2 * 38 * density));
        cardHeight = (int) (dm.heightPixels - (440 * density));

        swipeView = (SwipeFlingAdapterView) view.findViewById(R.id.swipe_view);

        if (swipeView != null) {
            swipeView.setIsNeedSwipe(true);
            swipeView.setFlingListener(this);
            swipeView.setOnItemClickListener(this);

            adapter = new InnerAdapter();
            swipeView.setAdapter(adapter);
        }
    }


    @Override
    public void onItemClicked(MotionEvent event, View v, Object dataObject) {
        Toast.makeText(getActivity(),"点击了",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void removeFirstObjectInAdapter() {
        adapter.remove(0);
    }

    @Override
    public void onLeftCardExit(Object dataObject) {
    }

    @Override
    public void onRightCardExit(Object dataObject) {
    }

    @Override
    public void onAdapterAboutToEmpty(int itemsInAdapter) {
        if (itemsInAdapter == 3) {
            loadData();
        }
    }

    @Override
    public void onScroll(float progress, float scrollXProgress) {
    }

    private void loadData() {
        new AsyncTask<Void, Void, List<Talent>>() {
            @Override
            protected List<Talent> doInBackground(Void... params) {
                ArrayList<Talent> list = new ArrayList<>(10);
                Talent talent;
                for (int i = 0; i < 10; i++) {
                    talent = new Talent();
                    talent.headerIcon = headerIcons[i % headerIcons.length];
                    talent.nickname = names[ran.nextInt(names.length-1)];
                    talent.cityName = citys[ran.nextInt(citys.length-1)];
                    list.add(talent);
                }
                return list;
            }

            @Override
            protected void onPostExecute(List<Talent> list) {
                super.onPostExecute(list);
                adapter.addAll(list);
            }
        }.execute();
    }


    private class InnerAdapter extends BaseAdapter {

        ArrayList<Talent> objs;

        public InnerAdapter() {
            objs = new ArrayList<>();
        }

        public void addAll(Collection<Talent> collection) {
            if (isEmpty()) {
                objs.addAll(collection);
                notifyDataSetChanged();
            } else {
                objs.addAll(collection);
            }
        }

        public void clear() {
            objs.clear();
            notifyDataSetChanged();
        }

        public boolean isEmpty() {
            return objs.isEmpty();
        }

        public void remove(int index) {
            if (index > -1 && index < objs.size()) {
                objs.remove(index);
                notifyDataSetChanged();
            }
        }


        @Override
        public int getCount() {
            return objs.size();
        }

        @Override
        public Talent getItem(int position) {
            if(objs==null ||objs.size()==0) return null;
            return objs.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        // TODO: getView
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            Talent talent = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.find_card_item, parent, false);
                holder  = new ViewHolder();
                convertView.setTag(holder);
                convertView.getLayoutParams().width = cardWidth;
                holder.portraitView = (ImageView) convertView.findViewById(R.id.find_img);
                holder.portraitView.getLayoutParams().height = cardHeight;
                holder.cityView = (TextView) convertView.findViewById(R.id.find_title);
                holder.nameView = (TextView) convertView.findViewById(R.id.find_jianjie);

            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            holder.portraitView.setImageResource(talent.headerIcon);
            holder.nameView.setText(String.format("%s", talent.nickname));
            holder.cityView.setText(talent.cityName);
            return convertView;
        }
    }

    private static class ViewHolder {
        ImageView portraitView;
        TextView nameView;
        TextView cityView;
    }
    public static class Talent {
        public int headerIcon;
        public String nickname;
        public String cityName;
    }
}

