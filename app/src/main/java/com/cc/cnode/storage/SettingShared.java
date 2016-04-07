package com.cc.cnode.storage;

import android.content.Context;

/**
 * 注释：设置信息的保存
 * 作者：菠菜 on 2016/4/7 14:26
 * 邮箱：971859818@qq.com
 */
public class SettingShared {

    public SettingShared() {}

    private static final String TAG = "SettingShared";

    private static final String KEY_ENABLE_NOTIFICATION = "notification";       // 消息推送
    private static final String KEY_ENABLE_THEME_DARK = "theme_dark";           // 黑色主题
    private static final String KEY_NEW_TOPIC_DRAFT = "new_topic_draft";        //　保存草稿
    private static final String KEY_ENABLE_TOPIC_SIGN = "topic_sign";           //  是否开启小尾巴
    private static final String KEY_TOPIC_SIGN_CONTENT = "topic_sign_content";  //  小尾巴内容

    public static final String DEFAULT_TOPIC_SIGN_CONTENT = "来自菠菜的CNode客户端[(https://github.com/caichaoping)]";      // 默认小尾巴

    //　消息推送
    public static boolean isEnableNotification(Context context) {
        return SharedWrapper.with(context, TAG).getBoolean(KEY_ENABLE_NOTIFICATION, true);
    }

    public static void setEnableNotification(Context context, boolean enable) {
        SharedWrapper.with(context, TAG).setBoolean(KEY_ENABLE_NOTIFICATION, enable);
    }

    //　主题
    public static boolean isEnableThemeDark(Context context) {
        return SharedWrapper.with(context, TAG).getBoolean(KEY_ENABLE_THEME_DARK, false);
    }

    public static void setEnableThemeDark(Context context, boolean enable) {
        SharedWrapper.with(context, TAG).setBoolean(KEY_ENABLE_THEME_DARK, enable);
    }

    // 保存草稿
    public static boolean isEnableNewTopicDraft(Context context) {
        return SharedWrapper.with(context, TAG).getBoolean(KEY_NEW_TOPIC_DRAFT, true);
    }

    public static void setEnableNewTopicDraft(Context context, boolean enable) {
        SharedWrapper.with(context, TAG).setBoolean(KEY_NEW_TOPIC_DRAFT, enable);
    }

    // 小尾巴
    public static boolean isEnableTopicSign(Context context) {
        return SharedWrapper.with(context, TAG).getBoolean(KEY_ENABLE_TOPIC_SIGN, true);
    }

    public static void setEnableTopicSign(Context context, boolean enable) {
        SharedWrapper.with(context, TAG).setBoolean(KEY_ENABLE_TOPIC_SIGN, enable);
    }

    // 小尾巴内容
    public static String getTopicSignContent(Context context) {
        return SharedWrapper.with(context, TAG).getString(KEY_TOPIC_SIGN_CONTENT, DEFAULT_TOPIC_SIGN_CONTENT);
    }

    public static void setTopicSignContent(Context context, String content) {
        SharedWrapper.with(context, TAG).setString(KEY_TOPIC_SIGN_CONTENT, content);
    }

}
