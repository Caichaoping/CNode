package com.cc.cnode.app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.cc.cnode.ui.activity.CrashLogActivity;

/**
 * 注释：错误信息接收 Handler
 * 作者：菠菜 on 2016/4/8 17:34
 * 邮箱：971859818@qq.com
 */
public class AppExceptionHandler implements Thread.UncaughtExceptionHandler {

    private Context context;

    public AppExceptionHandler(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public void uncaughtException(Thread thread, Throwable e) {
        Intent intent = new Intent(context, CrashLogActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Bundle bundle = new Bundle();
        bundle.putSerializable("e", e);
        intent.putExtras(bundle);
        context.startActivity(intent);
        System.exit(1);
    }

}
