package com.cc.cnode.util;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * 注释：打开发送工具类
 * 作者：菠菜 on 2016/4/8 17:13
 * 邮箱：971859818@qq.com
 */
public final class ShipUtils {

    private ShipUtils() {}

    // 打开应用商店
    public static void openInAppStore(Context context) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("market://details?id=" + context.getPackageName()));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else {
            T.showShort("您的系统中没有安装应用商店");
        }
    }

    // 打开系统浏览器
    public static void openInBrowser(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(intent);
        } else {
            T.showShort("您的系统中没有安装浏览器");
        }
    }

    // 发送 E-mail
    public static void sendEmail(Context context, String email, String subject, String text) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setData(Uri.parse("mailto:" + email));
        if (intent.resolveActivity(context.getPackageManager()) != null) {
            intent.putExtra(Intent.EXTRA_SUBJECT, subject);
            intent.putExtra(Intent.EXTRA_TEXT, text);
            context.startActivity(intent);
        } else {
            T.showShort("您的系统中没有安装邮件客户端");
        }
    }

}

