package com.cc.cnode.ui.base;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.cc.cnode.ui.widget.ThemeUtils;

/**
 * 注释：侧拉菜单Activity基类 DrawerLayoutActivity
 * 作者：菠菜 on 2016/4/7 10:32
 * 邮箱：971859818@qq.com
 */
public abstract class DrawerLayoutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 如果系统版本大于等于4.4，小于5.0
        if (Build.VERSION.SDK_INT  >= Build.VERSION_CODES.KITKAT && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            // 大于5.0
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_FULLSCREEN);

        }
    }

    // 适配4.4以上的状态栏
    public void adapterStatusBar(View statusBar){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
            layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
            layoutParams.height = ThemeUtils.getStatusBarHeight(this);
            statusBar.setLayoutParams(layoutParams);
        }
    }

























}
