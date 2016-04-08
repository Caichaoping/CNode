package com.cc.cnode.ui.listener;

import android.app.Activity;
import android.support.v4.app.ActivityCompat;
import android.view.View;

/**
 * 注释：menu点击后监听
 * 作者：菠菜 on 2016/4/8 17:03
 * 邮箱：971859818@qq.com
 */
public class NavigationFinishClickListener implements View.OnClickListener {

    private Activity activity;

    public NavigationFinishClickListener(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onClick(View v) {
        ActivityCompat.finishAfterTransition(activity);
    }

}
