package com.cc.cnode.ui.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;

import com.cc.cnode.ui.widget.ThemeUtils;

/**
 * 注释：
 * 作者：菠菜 on 2016/4/8 11:06
 * 邮箱：971859818@qq.com
 */
public final class DialogUtils {

    private DialogUtils() {}

    public static ProgressDialog createProgressDialog(Context context) {
        return new ProgressDialog(context, ThemeUtils.getDialogThemeRes(context));
    }

    public static AlertDialog.Builder createAlertDialogBuilder(Context context) {
        return new AlertDialog.Builder(context, ThemeUtils.getDialogThemeRes(context));
    }

}
