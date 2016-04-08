package com.cc.cnode.util;

import android.os.Handler;
import android.os.Looper;

/**
 * 注释：主线程消息发送工具类
 * 作者：菠菜 on 2016/4/8 09:49
 * 邮箱：971859818@qq.com
 */
public class HandlerUtils {

    private HandlerUtils() {}

    private static final Handler handler = new Handler(Looper.getMainLooper());

    public static boolean post(Runnable r) {
        return handler.post(r);
    }

    public static boolean postDelayed(Runnable r, long delayMillis) {
        return handler.postDelayed(r, delayMillis);
    }

}
