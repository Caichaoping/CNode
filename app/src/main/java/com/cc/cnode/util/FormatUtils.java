package com.cc.cnode.util;

import android.text.TextUtils;

import org.joda.time.DateTime;

import java.util.Date;

/**
 * 注释：格式化工具类
 * 作者：菠菜 on 2016/4/7 17:42
 * 邮箱：971859818@qq.com
 */
public final class FormatUtils {

    public FormatUtils() {}

    /**
     * 获取最近时间字符串
     */

    private static final long minute = 60 * 1000;
    private static final long hour = 60 * minute;
    private static final long day = 24 * hour;
    private static final long week = 7 * day;
    private static final long month = 31 * day;
    private static final long year = 12 * month;

    public static String getRecentlyTimeText(DateTime dateTime) {
        if (dateTime == null) {
            return null;
        }
        long diff = new Date().getTime() - dateTime.getMillis();
        long r;
        if (diff > year) {
            r = (diff / year);
            return r + "年前";
        }
        if (diff > month) {
            r = (diff / month);
            return r + "个月前";
        }
        if (diff > week) {
            r = (diff / week);
            return r + "周前";
        }
        if (diff > day) {
            r = (diff / day);
            return r + "天前";
        }
        if (diff > hour) {
            r = (diff / hour);
            return r + "小时前";
        }
        if (diff > minute) {
            r = (diff / minute);
            return r + "分钟前";
        }
        return "刚刚";
    }

    /**
     * 修复头像地址的历史遗留问题
     */
    public static String getCompatAvatarUrl(String avatarUrl) {
        if (!TextUtils.isEmpty(avatarUrl) && avatarUrl.startsWith("//gravatar.com/avatar/")) {
            return "http:" + avatarUrl;
        } else {
            return avatarUrl;
        }
    }
}
