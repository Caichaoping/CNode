package com.cc.cnode.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.cc.cnode.R;
import com.cc.cnode.ui.base.StatusBarActivity;
import com.cc.cnode.ui.listener.NavigationFinishClickListener;
import com.cc.cnode.ui.widget.ThemeUtils;
import com.cc.cnode.util.ShipUtils;

import org.joda.time.DateTime;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 注释：崩溃显示界面
 * 作者：菠菜 on 2016/4/8 16:48
 * 邮箱：971859818@qq.com
 */
public class CrashLogActivity extends StatusBarActivity implements Toolbar.OnMenuItemClickListener {

    @Bind(R.id.crash_log_toolbar)
    protected Toolbar toolbar;

    @Bind(R.id.crash_log_tv_info)
    protected TextView tvInfo;

    private String crashLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.configThemeBeforeOnCreate(this, R.style.AppThemeLight, R.style.AppThemeDark);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crash_log);
        ButterKnife.bind(this);

        toolbar.setNavigationOnClickListener(new NavigationFinishClickListener(this));
        toolbar.inflateMenu(R.menu.new_topic);
        toolbar.setOnMenuItemClickListener(this);

        //接收异常对象
        Intent intent = getIntent();
        Throwable e = (Throwable) intent.getSerializableExtra("e");

        //构建字符串
        StringBuilder sb = new StringBuilder();
        sb.append("生产厂商：\n");
        sb.append(Build.MANUFACTURER).append("\n\n");
        sb.append("手机型号：\n");
        sb.append(Build.MODEL).append("\n\n");
        sb.append("系统版本：\n");
        sb.append(Build.VERSION.RELEASE).append("\n\n");
        sb.append("异常时间：\n");
        sb.append(new DateTime()).append("\n\n");
        sb.append("异常类型：\n");
        sb.append(e.getClass().getName()).append("\n\n");
        sb.append("异常信息：\n");
        sb.append(e.getMessage()).append("\n\n");
        sb.append("异常堆栈：\n");
        Writer writer = new StringWriter();
        PrintWriter printWriter = new PrintWriter(writer);
        e.printStackTrace(printWriter);
        Throwable cause = e.getCause();
        while (cause != null) {
            cause.printStackTrace(printWriter);
            cause = cause.getCause();
        }
        printWriter.close();
        sb.append(writer.toString());
        crashLog = sb.toString();
        //显示信息
        tvInfo.setText(crashLog);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_send:
                ShipUtils.sendEmail(
                        this,
                        "971859818@qq.com",
                        "来自 CNodeMD-" + AboutActivity.VERSION_TEXT + " 的客户端崩溃日志",
                        crashLog);
                return true;
            default:
                return false;
        }
    }

}

