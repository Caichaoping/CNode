package com.cc.cnode.ui.activity;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.cnode.R;
import com.cc.cnode.model.entity.TabType;
import com.cc.cnode.model.entity.Topic;
import com.cc.cnode.ui.adapter.MainAdapter;
import com.cc.cnode.ui.base.DrawerLayoutActivity;
import com.cc.cnode.ui.listener.RecycleViewLoadMoreListener;
import com.cc.cnode.ui.widget.ThemeUtils;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends DrawerLayoutActivity implements SwipeRefreshLayout.OnRefreshListener,RecycleViewLoadMoreListener.OnLoadMoreListener{

    // 抽屉导航布局
    @Bind(R.id.main_drawer_layout)
    DrawerLayout mDrawerLayout;

    //状态栏
    @Bind(R.id.main_center_adapt_status_bar)
    View mCenterAdaptStatusBar;

    @Bind(R.id.main_nav_adapt_status_bar)
    View mNavAdaptStatusBar;

    //导航部分的个人信息
    @Bind(R.id.main_nav_img_avatar)
    CircleImageView mNavImgAvatar;

    @Bind(R.id.main_nav_tv_login_name)
    TextView mNavTvLoginName;

    @Bind(R.id.main_nav_tv_score)
    TextView mNavTvScore;

    @Bind(R.id.main_nav_tv_badger_notification)
    TextView mNavTvBadgerNotification;

    @Bind(R.id.main_nav_btn_logout)
    TextView mNavBtnLogout;

    @Bind(R.id.main_nav_btn_theme_dark)
    ImageView mNavBtnThemeDark;

    @Bind(R.id.main_nav_img_top_background)
    ImageView mNavImgTopBackground;

    // 主要导航项
    @Bind({
            R.id.main_nav_btn_all,
            R.id.main_nav_btn_good,
            R.id.main_nav_btn_share,
            R.id.main_nav_btn_ask,
            R.id.main_nav_btn_job,

    })
    protected List<CheckedTextView> navMainItemList;

    // 内容部分
    @Bind(R.id.main_toolbar)
    Toolbar mToolbar;

    @Bind(R.id.main_icon_no_data)
    TextView mIconNoData;

    @Bind(R.id.main_recycler_view)
    RecyclerView mRecyclerView;

    @Bind(R.id.main_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    @Bind(R.id.main_fab_new_topic)
    FloatingActionButton mFabNewTopic;

    // 当前板块 默认为all
    private TabType currentTab = TabType.all;
    private int cuttentPage = 0; // 未加载
    private List<Topic> topicList = new ArrayList<>();
    private MainAdapter adapter;

    // 首次按下返回键时间戳
    private long firstBackPressedTime = 0;

    // 是否启用夜间模式
    private boolean enableThemeDark;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        enableThemeDark = ThemeUtils.configThemeBeforeOnCreate(this,R.style.AppThemeLight_FitsStatusBar,R.style.AppThemeDark_FitsStatusBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        adapterStatusBar(mCenterAdaptStatusBar);
        adapterStatusBar(mNavAdaptStatusBar);

        mDrawerLayout.setDrawerShadow(R.drawable.navigation_drawer_shadow, GravityCompat.START);
        mDrawerLayout.addDrawerListener(openDrawerListener);

    }

    /**
     * 用户信息更新逻辑
     */
    private  DrawerLayout.DrawerListener openDrawerListener = new DrawerLayout.SimpleDrawerListener() {
        @Override
        public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
        }
    };

    // 次导航项
    @OnClick({R.id.main_nav_btn_setting, R.id.main_nav_btn_about, R.id.main_nav_btn_notification})
    public void onNavItemOtherClick(View view) {
        switch (view.getId()) {
            case R.id.main_nav_btn_notification:
                break;
            case R.id.main_nav_btn_setting:
                break;
            case R.id.main_nav_btn_about:
                break;
            default:
                break;
        }
    }

    // 其他菜单
    @OnClick({R.id.main_fab_new_topic, R.id.main_nav_layout_info, R.id.main_nav_btn_logout, R.id.main_nav_btn_theme_dark})
    public void onClick(View view) {
        switch (view.getId()) {
            //　发帖
            case R.id.main_fab_new_topic:
                break;
            // 登录信息
            case R.id.main_nav_layout_info:
                break;
            // 注销
            case R.id.main_nav_btn_logout:
                break;
            // 主题切换
            case R.id.main_nav_btn_theme_dark:
                break;
        }
    }

    /**
     * 下拉刷新
     */

    @Override
    public void onRefresh() {

    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMore() {

    }
}
