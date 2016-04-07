package com.cc.cnode.storage;

import android.content.Context;
import android.text.TextUtils;

import com.cc.cnode.util.codec.Digest;

import java.util.UUID;

/**
 * 注释：（保存的）设备信息--MD5加密后的
 * 作者：菠菜 on 2016/4/7 11:49
 * 邮箱：971859818@qq.com
 */
public final  class DeviceInfo {

    public DeviceInfo() {}

    private static  final String TAG = "DeviceInfo";
    private static  final String KEY_DEVICE_TOKEN = "deviceToken";
    private volatile static String deviceToken = null;

    public static  String getDeviceToken(Context context){
        if (TextUtils.isEmpty(deviceToken)){
            synchronized (DeviceInfo.class){
                if (TextUtils.isEmpty(deviceToken)){
                    deviceToken = context.getSharedPreferences(Digest.MD5.getMessage(TAG),Context.MODE_PRIVATE)
                    .getString(KEY_DEVICE_TOKEN,null);
                }
                if (TextUtils.isEmpty(deviceToken)){
                    // UUID含义是通用唯一识别码，巨量数据不重复
                    deviceToken = Digest.MD5.getMessage(UUID.randomUUID().toString());
                    context.getSharedPreferences(Digest.MD5.getMessage(TAG),Context.MODE_PRIVATE).edit().putString(KEY_DEVICE_TOKEN,deviceToken);

                }
            }
        }
        return deviceToken;
    }

}
