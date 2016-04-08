package com.cc.cnode.ui.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.cc.cnode.R;
import com.cc.cnode.storage.SettingShared;

/**
 * 注释：主题工具类
 * 作者：菠菜 on 2016/4/7 10:38
 * 邮箱：971859818@qq.com
 */
public class ThemeUtils {

    private ThemeUtils(){}

    // 获取主题颜色
    public static  int getThemeAttrColor(Context context, int attr){
        TypedArray a = context.obtainStyledAttributes(null, new int[]{attr});
        try {
            return a.getColor(0,0);
        }finally {
            a.recycle();
        }

    }

    // 获取主题 Drawable
    public static Drawable getThemeAttrDrawable(Context context,int attr){
        TypedArray a = context.obtainStyledAttributes(null ,new int[]{attr});
        try{
            return  a.getDrawable(0);

        }finally {
            a.recycle();
        }
    }

    // Activity 启动前配置主题 返回是否为黑色主题
    public static boolean configThemeBeforeOnCreate(Activity activity, int light,int dark){
        boolean enable = SettingShared.isEnableThemeDark(activity);
        activity.setTheme(enable?dark:light);
        return  enable;
    }

    // 重启 Activity
    public static void recreateActivity(Activity activity){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            activity.recreate();
        }else {
            Intent intent = activity.getIntent();
            intent.setClass(activity,activity.getClass());
            activity.startActivity(intent);
            activity.finish();
            activity.overridePendingTransition(0,0);
        }
    }

    // 获取当前主题下的 dialog 主题
    public static int getDialogThemeRes(Context context){
        return SettingShared.isEnableThemeDark(context)? R.style.AppDialogDark:R.style.AppDialogLight;
    }

    // 获取状态栏高度
    public static int getStatusBarHeight(Context context){
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        return resourceId>0 ? context.getResources().getDimensionPixelOffset(resourceId):0 ;
    }


}






















