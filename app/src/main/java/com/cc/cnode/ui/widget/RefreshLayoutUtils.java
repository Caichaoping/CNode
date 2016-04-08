package com.cc.cnode.ui.widget;

import android.support.v4.widget.SwipeRefreshLayout;

import com.cc.cnode.R;
import com.cc.cnode.util.HandlerUtils;

/**
 * 注释：下拉刷新控件工具类
 * 作者：菠菜 on 2016/4/8 09:48
 * 邮箱：971859818@qq.com
 */
public class RefreshLayoutUtils {

    private RefreshLayoutUtils() {}

    public static void initOnCreate(SwipeRefreshLayout refreshLayout, SwipeRefreshLayout.OnRefreshListener refreshListener) {
        refreshLayout.setColorSchemeResources(R.color.red_light, R.color.green_light, R.color.blue_light, R.color.orange_light);
        refreshLayout.setOnRefreshListener(refreshListener);
    }

    /**
     * TODO refreshLayout无法直接在onCreate中设置刷新状态
     */
    public static void refreshOnCreate(final SwipeRefreshLayout refreshLayout, final SwipeRefreshLayout.OnRefreshListener refreshListener) {
        HandlerUtils.postDelayed(new Runnable() {

            @Override
            public void run() {
                refreshLayout.setRefreshing(true);
                refreshListener.onRefresh();
            }

        }, 100);
    }

}
