package com.cc.cnode.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.cc.cnode.R;
import com.cc.cnode.ui.base.StatusBarActivity;
import com.cc.cnode.ui.listener.NavigationFinishClickListener;
import com.cc.cnode.ui.widget.ThemeUtils;
import com.cc.cnode.util.ResRawUtils;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 注释：
 * 作者：菠菜 on 2016/4/8 17:17
 * 邮箱：971859818@qq.com
 */
public class LicenseActivity  extends StatusBarActivity {

    @Bind(R.id.license_toolbar)
    protected Toolbar toolbar;

    @Bind(R.id.license_tv_license)
    protected TextView tvLicense;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ThemeUtils.configThemeBeforeOnCreate(this, R.style.AppThemeLight, R.style.AppThemeDark);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_license);
        ButterKnife.bind(this);

        toolbar.setNavigationOnClickListener(new NavigationFinishClickListener(this));

        tvLicense.setText(ResRawUtils.getString(this, R.raw.open_source));
    }

}
