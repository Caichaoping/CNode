package com.cc.cnode.ui.base;

import android.support.v7.app.AppCompatActivity;

import com.umeng.analytics.MobclickAgent;

/**
 * 注释：Activity 基类
 * 作者：菠菜 on 2016/4/7 10:33
 * 邮箱：971859818@qq.com
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this); // 友盟统计
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);  // 友盟统计
    }
}
