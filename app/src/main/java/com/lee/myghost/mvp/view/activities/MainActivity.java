package com.lee.myghost.mvp.view.activities;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hjm.bottomtabbar.BottomTabBar;
import com.jaeger.library.StatusBarUtil;
import com.lee.myghost.R;
import com.lee.myghost.mvp.model.base.BaseAvtivity;
import com.lee.myghost.mvp.model.base.BaseFragment;
import com.lee.myghost.mvp.model.contract.viewinter.GetDataFromNetInter;
import com.lee.myghost.mvp.presenter.GetDataPresenter;
import com.lee.myghost.mvp.view.adapters.ThemeAdapter;
import com.lee.myghost.mvp.view.fragments.ChoicenessFragment;
import com.lee.myghost.mvp.view.fragments.FindFragment;
import com.lee.myghost.mvp.view.fragments.MineFragment;
import com.lee.myghost.mvp.view.fragments.SpeicalFragment;
import com.lee.myghost.utils.CommonUtil;
import com.lee.myghost.utils.ShareUtil;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;

public class MainActivity extends BaseAvtivity<GetDataPresenter> implements GetDataFromNetInter,View.OnClickListener {

    String ANDROID_ID = android.os.Build.MODEL;
    private FrameLayout         main_framlayout;
    private BottomTabBar main_radiogroup;
    private FragmentManager     manager;
    private FragmentTransaction transaction;
    private ChoicenessFragment  mChoicenessFragment;
    private SpeicalFragment     mSpeicalFragment;
    private MineFragment        mMineFragment;
    private FindFragment        mFindFragment;
    private RelativeLayout      my_download, my_collect, my_welfare, my_share, my_suggest, slide_about, slide_theme;
    private Intent   intent;
    /**
     * 反馈
     */
    private TextView mSuggestTitle;
    /**
     * 请输入您的邮箱(必填)
     */
    private EditText mSuggestEmail;
    private View     mSuggestView;
    /**
     * 请输入您的反馈...
     */
    private EditText mSuggestFeedback;
    /**
     * 按住录音
     */
    private Button   mHoldTheTape;
    /**
     * 上传屏幕截图
     */
    private CheckBox mUploadingScreenshot;
    /**
     * 设备详情
     */
    private TextView mGetPhoneInfo;
    private int clickPosition=0;
    private View parentView;
    private LinearLayout side_linearlayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TAG", "setChildContentView: " + "执行onCreate");
        parentView = View.inflate(this, R.layout.activity_main, null);
        initView();
        initData();
    }

    @Override
    public void initView() {
        main_framlayout = findViewById(R.id.main_framlayout);
        main_radiogroup = findViewById(R.id.main_radiogroup);
        my_collect = findViewById(R.id.my_collect);
        side_linearlayout = findViewById(R.id.side_linearlayout);
        my_download = findViewById(R.id.my_download);
        my_welfare = findViewById(R.id.my_welfare);
        my_share = findViewById(R.id.my_share);
        my_suggest = findViewById(R.id.my_suggest);
        slide_about = findViewById(R.id.slide_about);
        slide_theme = findViewById(R.id.slide_theme);
        my_collect.setOnClickListener(this);
        my_download.setOnClickListener(this);
        my_welfare.setOnClickListener(this);
        my_share.setOnClickListener(this);
        my_suggest.setOnClickListener(this);
        slide_about.setOnClickListener(this);
        slide_theme.setOnClickListener(this);
    }

    @Override
    public void initData() {
        main_radiogroup.init(getSupportFragmentManager())
                .setImgSize(80, 80)
                .setFontSize(10)
                .setTabPadding(4, 6, 10)
                .setChangeColor(Color.RED, Color.DKGRAY)
                .setTabBarBackgroundResource(R.mipmap.bottom_bg)
                .addTabItem("精选", R.mipmap.found_select, R.mipmap.found, ChoicenessFragment.class)
                .addTabItem("专题", R.mipmap.special_select, R.mipmap.special, SpeicalFragment.class)
                .addTabItem("发现", R.mipmap.fancy_select, R.mipmap.fancy, FindFragment.class)
                .addTabItem("我的", R.mipmap.my_select, R.mipmap.my, MineFragment.class)
                .isShowDivider(false);
    }

    @Override
    public GetDataPresenter setPresenter() {
        return new GetDataPresenter(this);
    }

    @Override
    public int setChildContentView() {
        Log.d("TAG", "setChildContentView: " + "执行");
        return R.layout.activity_main;
    }

    @Override
    public void onSuccess(ResponseBody responseBody) {

    }

    @Override
    public void onError(Throwable throwable) {
        Log.d("MainActivity", "throwable:" + throwable);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.my_collect:
                intent = new Intent(MainActivity.this, CollectActivity.class);
                startActivity(intent);
                break;
            case R.id.my_download:
                Toast.makeText(MainActivity.this, "待开发", Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_welfare:
                intent = new Intent(MainActivity.this, WelfareActivity.class);
                startActivity(intent);
                break;
            case R.id.my_share:
                ShareUtil.shareText(this, "这是我的App", "分享到");
                break;
            case R.id.my_suggest:
                popWindowMethod();
                break;
            case R.id.slide_about:
                intent = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.slide_theme:
                showSelectThemes();
                break;
            case R.id.hold_the_tape:
                break;
        }

    }

    private void showSelectThemes() {
        clickPosition = 0;
        final ArrayList<Integer> colorData = getColorData();
        View view = View.inflate(this, R.layout.theme_view, null);
        GridView gridView = view.findViewById(R.id.theme_gridView);
        final ThemeAdapter themeAdapter = new ThemeAdapter(getColorData(), 0, this);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                themeAdapter.setPosition(position);
                clickPosition = position;
                themeAdapter.notifyDataSetChanged();
                int colors = colorData.get(position);
                side_linearlayout.setBackgroundColor(getResources().getColor(colors));
            }
        });
        gridView.setAdapter(themeAdapter);

        new AlertDialog.Builder(this)
                .setView(view)
                .setPositiveButton("取消", null)
                .setNegativeButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int color = getResources().getColor(colorData.get(clickPosition));
                        StatusBarUtil.setColor(MainActivity.this, color);
                        if (parentView != null)
                            parentView.setBackgroundColor(color);
                        CommonUtil.saveColorValue(color);

                        if (mSpeicalFragment != null){
                            mSpeicalFragment.setTitleBackGround(color);
                        }
                        if (mFindFragment != null){
                            mFindFragment.setTitleBackGround(color);
                        }
                        if (mMineFragment != null){
                            mMineFragment.setTitleBackGround(color);
                        }

                    }
                })
                .create()
                .show();
    }

    private ArrayList<Integer> getColorData() {
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(R.color.colorBluePrimaryDark);
        integers.add(R.color.colorAccent);
        integers.add(R.color.colorTealPrimary);
        integers.add(R.color.colorDeepOrangePrimary);
        integers.add(R.color.colorRedPrimaryCenter);
        integers.add(R.color.colorRedPrimary);
        integers.add(R.color.colorPrimaryDark);
        integers.add(R.color.colorPrimary);
        integers.add(R.color.colorLimePrimaryCenter);
        integers.add(R.color.colorOrangePrimary);
        integers.add(R.color.colorSecondText);
        integers.add(R.color.colorLimePrimaryDark);
        integers.add(R.color.colorDeepPurplePrimaryCenter);
        integers.add(R.color.colorHint);
        integers.add(R.color.colorDeepOrangePrimaryCenter);
        integers.add(R.color.colorSecondText);
        return integers;
    }

    public void popWindowMethod() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        View view = View.inflate(this, R.layout.suggest_dialog_item, null);
        mSuggestTitle = (TextView) view.findViewById(R.id.suggest_title);
        mSuggestEmail = (EditText) view.findViewById(R.id.suggest_email);
        mSuggestView = (View) view.findViewById(R.id.suggest_view);
        mSuggestFeedback = (EditText) view.findViewById(R.id.suggest_feedback);
        mHoldTheTape = (Button) view.findViewById(R.id.hold_the_tape);
        mHoldTheTape.setOnClickListener(this);
        mUploadingScreenshot = (CheckBox) view.findViewById(R.id.uploading_screenshot);
        mGetPhoneInfo = (TextView) view.findViewById(R.id.get_phone_info);
        mGetPhoneInfo.setText("设备型号：  " + getSystemModel() + "  " + getSystemVersion());
        alertDialogBuilder.setView(view).setPositiveButton("取消", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        }).setNegativeButton("发送", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

            }
        }).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (parentView != null) {
            int colorStyle = CommonUtil.obtainColorValue();
            if (colorStyle != -1) {
                parentView.setBackgroundColor(colorStyle);
            }
        }
    }

    /*两次退出*/
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {
                //弹出提示，可以有多种方式
                Toast.makeText(getApplicationContext(), "再按一次退出程序", Toast.LENGTH_SHORT).show();
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return false;
        }

        return super.onKeyDown(keyCode, event);
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

}
