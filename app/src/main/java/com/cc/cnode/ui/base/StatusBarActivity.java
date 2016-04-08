package com.cc.cnode.ui.base;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;

import com.cc.cnode.R;
import com.cc.cnode.ui.widget.ThemeUtils;

/**
 * 注释：带状态栏界面基类
 * 作者：菠菜 on 2016/4/8 16:40
 * 邮箱：971859818@qq.com
 */
public class StatusBarActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 版本号大于等于4.4，小于5.0
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            FrameLayout rootView = new FrameLayout(this);
            rootView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

            View contentView = LayoutInflater.from(this).inflate(layoutResID, rootView, false);
            contentView.setFitsSystemWindows(true);
            rootView.addView(contentView);

            View statusBarView = new View(this);
            statusBarView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ThemeUtils.getStatusBarHeight(this)));
            statusBarView.setBackgroundColor(ThemeUtils.getThemeAttrColor(this, R.attr.colorPrimaryDark));
            rootView.addView(statusBarView);

            super.setContentView(rootView);
        } else {
            super.setContentView(layoutResID);
        }
    }
}






































