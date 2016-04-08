package com.cc.cnode.model.api;

import android.os.Build;

import com.cc.cnode.BuildConfig;

/**
 * 注释：API 定义
 * 作者：菠菜 on 2016/4/8 14:40
 * 邮箱：971859818@qq.com
 */
public class ApiDefine {

    public ApiDefine() {}

    public static final String API_HOST = "https://cnodejs.org/api";         // api 地址

    /**
     * 请求添加的头信息
     * User-Agent: CNodeMD/1.0 (Android 4.1.1; Xiaomi - MI 2S)
     *  Accept: application/json
     */

    public static final String USER_AGENT = "Bocai/"+ BuildConfig.VERSION_NAME+ " (Android"+ Build.VERSION.RELEASE + "; "+ Build.MANUFACTURER + "-" +Build.MODEL + ")";
    public static final String HTTP_ACCEPT = "application/json";
}
