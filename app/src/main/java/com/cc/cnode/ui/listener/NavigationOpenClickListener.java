package com.cc.cnode.ui.listener;

import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.View;

/**
 * 注释：用作左边菜单图标监听
 * 作者：菠菜 on 2016/4/8 16:25
 * 邮箱：971859818@qq.com
 */
public class NavigationOpenClickListener implements View.OnClickListener {

    private DrawerLayout drawerLayout;

    public NavigationOpenClickListener(DrawerLayout drawerLayout) {
        this.drawerLayout = drawerLayout;
    }

    @Override
    public void onClick(View v) {
        drawerLayout.openDrawer(GravityCompat.START);
    }

}