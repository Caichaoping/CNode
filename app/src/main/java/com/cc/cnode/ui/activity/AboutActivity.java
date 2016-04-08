package com.cc.cnode.ui.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.cc.cnode.BuildConfig;
import com.cc.cnode.R;
import com.cc.cnode.ui.base.StatusBarActivity;
import com.cc.cnode.ui.listener.NavigationFinishClickListener;
import com.cc.cnode.ui.widget.ThemeUtils;
import com.cc.cnode.util.ShipUtils;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 注释：关于页面
 * 作者：菠菜 on 2016/4/8 10:43
 * 邮箱：971859818@qq.com
 */
public class AboutActivity extends StatusBarActivity {

    public static final String VERSION_TEXT = BuildConfig.VERSION_NAME + "-build-" + BuildConfig.VERSION_CODE;

    @Bind(R.id.about_toolbar)
    protected Toolbar toolbar;

    @Bind(R.id.about_tv_version)
    protected TextView tvVersion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.configThemeBeforeOnCreate(this, R.style.AppThemeLight_FitsStatusBar, R.style.AppThemeDark_FitsStatusBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        ButterKnife.bind(this);

        toolbar.setNavigationOnClickListener(new NavigationFinishClickListener(this));

        tvVersion.setText(VERSION_TEXT);
    }

    @OnClick(R.id.about_btn_version)
    protected void onBtnVersionClick() {
        // TODO 检查更新
        //UpdateUtils.forceUpdate(this);
    }

    @OnClick(R.id.about_btn_open_source_url)
    protected void onBtnOpenSourceUrlClick() {
        ShipUtils.openInBrowser(this, getString(R.string.open_source_url_content));
    }

    @OnClick(R.id.about_btn_about_cnode)
    protected void onBtnAboutCNodeClick() {
        ShipUtils.openInBrowser(this, getString(R.string.about_cnode_content));
    }

    @OnClick(R.id.about_btn_about_author)
    protected void onBtnAboutAuthorClick() {
        ShipUtils.openInBrowser(this, getString(R.string.about_author_content));
    }

    @OnClick(R.id.about_btn_open_in_app_store)
    protected void onBtnOpenInAppStoreClick() {
        ShipUtils.openInAppStore(AboutActivity.this);
    }

    @OnClick(R.id.about_btn_advice_feedback)
    protected void onBtnAdviceFeedbackClick() {
        ShipUtils.sendEmail(
                this,
                "971859818@qq.com",
                "来自菠菜-" + VERSION_TEXT + " 的客户端反馈",
                "设备信息：Android " + Build.VERSION.RELEASE + " - " + Build.MANUFACTURER + " - " + Build.MODEL + "\n（如果涉及隐私请手动删除这个内容）\n\n");
    }

    @OnClick(R.id.about_btn_open_source_license)
    protected void onBtnOpenSourceLicenseClick() {
        startActivity(new Intent(this, LicenseActivity.class));
    }

    @OnClick(R.id.about_tv_version)
    public void onClick() {
        throw new RuntimeException("ddd");
    }
}
