package com.cc.cnode.app;

import android.app.Application;
import android.content.Context;

/**
 * 注释：全局Application
 * 作者：菠菜 on 2016/4/7 09:11
 * 邮箱：971859818@qq.com
 */
public class AppController extends Application {

    public static Context mContext;

    public static Context getContext() {
        return mContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;

        // 配置全局异常捕捉
//        if (!BuildConfig.DEBUG) {
            Thread.setDefaultUncaughtExceptionHandler(new AppExceptionHandler(this));
//        }

        // 友盟账号统计
//        if (!TextUtils.isEmpty(LoginShared.getAccessToken(this))) {
//            MobclickAgent.onProfileSignIn(LoginShared.getLoginName(this));
//        } else {
//            MobclickAgent.onProfileSignOff();
//        }
    }
}
