package com.cc.cnode.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

import com.cc.cnode.R;
import com.cc.cnode.model.api.ApiClient;
import com.cc.cnode.model.entity.Result;
import com.cc.cnode.model.entity.TabType;
import com.cc.cnode.model.entity.Topic;
import com.cc.cnode.storage.LoginShared;
import com.cc.cnode.storage.SettingShared;
import com.cc.cnode.ui.adapter.MainAdapter;
import com.cc.cnode.ui.base.DrawerLayoutActivity;
import com.cc.cnode.ui.dialog.DialogUtils;
import com.cc.cnode.ui.listener.NavigationOpenClickListener;
import com.cc.cnode.ui.listener.RecycleViewLoadMoreListener;
import com.cc.cnode.ui.widget.RefreshLayoutUtils;
import com.cc.cnode.ui.widget.ThemeUtils;
import com.cc.cnode.util.L;
import com.cc.cnode.util.T;
import com.melnykov.fab.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends DrawerLayoutActivity implements SwipeRefreshLayout.OnRefreshListener, RecycleViewLoadMoreListener.OnLoadMoreListener {

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
    private int currentPage = 0; // 未加载
    private List<Topic> topicList = new ArrayList<>();
    private MainAdapter adapter;

    // 首次按下返回键时间戳
    private long firstBackPressedTime = 0;

    // 是否启用夜间模式
    private boolean enableThemeDark;
    private ActionBarDrawerToggle mDrawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        enableThemeDark = ThemeUtils.configThemeBeforeOnCreate(this, R.style.AppThemeLight_FitsStatusBar, R.style.AppThemeDark_FitsStatusBar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(mToolbar);

        adapterStatusBar(mCenterAdaptStatusBar);
        adapterStatusBar(mNavAdaptStatusBar);

        mDrawerLayout.setDrawerShadow(R.drawable.navigation_drawer_shadow, GravityCompat.START);
        mDrawerLayout.setDrawerListener(openDrawerListener);
        mToolbar.setNavigationOnClickListener(new NavigationOpenClickListener(mDrawerLayout));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new MainAdapter(this, topicList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnScrollListener(new RecycleViewLoadMoreListener(linearLayoutManager, this, 20));
        mFabNewTopic.attachToRecyclerView(mRecyclerView);

        updateUserInfoViews(); // 更新用户信息

        mNavBtnThemeDark.setImageResource(enableThemeDark ? R.drawable.ic_wb_sunny_white_24dp : R.drawable.ic_brightness_3_white_24dp);
        mNavImgTopBackground.setVisibility(enableThemeDark ? View.INVISIBLE : View.VISIBLE);

        RefreshLayoutUtils.initOnCreate(mRefreshLayout, this);
        RefreshLayoutUtils.refreshOnCreate(mRefreshLayout, this);

        //版本检查
        // TODO
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMessageCountAsyncTask();
        // 判断是否切换了主题,如果切换了重启Activity，enableThemeDark也变成当前设置
        if (SettingShared.isEnableThemeDark(this) != enableThemeDark) {
            ThemeUtils.recreateActivity(this);
        }
    }

    // 同步服务端未读消息数目
    private void getMessageCountAsyncTask() {
        // TODO
    }

    /**
     * 打开侧边栏，
     * 1、根据sp信息更新用户信息
     * 2、同步服务器用户信息，保存至sp并更新用户信息
     * 3、同步服务器消息数目
     */
    private DrawerLayout.DrawerListener openDrawerListener = new DrawerLayout.SimpleDrawerListener() {
        @Override
        public void onDrawerOpened(View drawerView) {
            super.onDrawerOpened(drawerView);
            updateUserInfoViews();
            getUserAsyncTask();
            getMessageCountAsyncTask();
        }
    };

    // 更新用户信息
    private void updateUserInfoViews() {

    }

    // 同步服务端用户信息
    private void getUserAsyncTask() {

    }

    /**
     * 主菜单项点击事件
     * 点击后，设置关闭时候的执行的事件
     * 循环设置item是否选中
     * 关闭侧边栏，执行刚刚设置的事件
     * 执行完设置正常打开监听
     *
     */
    @OnClick({R.id.main_nav_btn_all, R.id.main_nav_btn_good, R.id.main_nav_btn_share, R.id.main_nav_btn_ask, R.id.main_nav_btn_job})
    public void onNavMainItemClick(View view) {
        switch (view.getId()) {
            case R.id.main_nav_btn_all:
                mDrawerLayout.setDrawerListener(tabAllDrawerListener);
                break;
            case R.id.main_nav_btn_good:
                mDrawerLayout.setDrawerListener(tabGoodDrawerListener);
                break;
            case R.id.main_nav_btn_share:
                mDrawerLayout.setDrawerListener(tabShareDrawerListener);
                break;
            case R.id.main_nav_btn_ask:
                mDrawerLayout.setDrawerListener(tabAskDrawerListener);
                break;
            case R.id.main_nav_btn_job:
                mDrawerLayout.setDrawerListener(tabJobDrawerListener);
                break;
            default:
                mDrawerLayout.setDrawerListener(openDrawerListener);
                break;
        }
        for (CheckedTextView navItem : navMainItemList){
            navItem.setChecked(navItem.getId() == view.getId());
        }
        mDrawerLayout.closeDrawers();
    }

    private class MainItemDrawerListener extends DrawerLayout.SimpleDrawerListener{
            private TabType tabType;

        protected MainItemDrawerListener(TabType tabType) {
            this.tabType = tabType;
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            if (tabType != currentTab){
                currentTab = tabType;
                currentPage = 0;
                mToolbar.setTitle(currentTab.getNameId());
                topicList.clear();
                notifyDataSetChanged();
                mRefreshLayout.setRefreshing(true);
                onRefresh();
                mFabNewTopic.show(true);
            }
            mDrawerLayout.setDrawerListener(openDrawerListener);
        }
    }

    private DrawerLayout.DrawerListener tabAllDrawerListener = new MainItemDrawerListener(TabType.all);
    private DrawerLayout.DrawerListener tabGoodDrawerListener = new MainItemDrawerListener(TabType.good);
    private DrawerLayout.DrawerListener tabShareDrawerListener = new MainItemDrawerListener(TabType.share);
    private DrawerLayout.DrawerListener tabAskDrawerListener = new MainItemDrawerListener(TabType.ask);
    private DrawerLayout.DrawerListener tabJobDrawerListener = new MainItemDrawerListener(TabType.job);


    // 次导航项点击事件，逻辑同主导航菜单
    @OnClick({R.id.main_nav_btn_setting, R.id.main_nav_btn_about, R.id.main_nav_btn_notification})
    public void onNavItemOtherClick(View view) {
        switch (view.getId()) {
            case R.id.main_nav_btn_notification: // 消息
                if (TextUtils.isEmpty(LoginShared.getAccessToken(this))){
                    showNeedLoginDialog();
                }else {
                    mDrawerLayout.setDrawerListener(notificationDrawerListener);
                    mDrawerLayout.closeDrawers();
                }
                break;
            case R.id.main_nav_btn_setting:  // 设置
                mDrawerLayout.setDrawerListener(settingDrawerListener);
                mDrawerLayout.closeDrawers();
                break;
            case R.id.main_nav_btn_about: // 关于
                mDrawerLayout.setDrawerListener(aboutDrawerListener);
                mDrawerLayout.closeDrawers();
                break;
            default:
                mDrawerLayout.setDrawerListener(openDrawerListener);
                mDrawerLayout.closeDrawers();
                break;
        }
    }

    private class OtherItemDrawerListener extends DrawerLayout.SimpleDrawerListener{
        private Class gotoClz;

        protected OtherItemDrawerListener(Class gotoClz) {
            this.gotoClz = gotoClz;
        }

        @Override
        public void onDrawerClosed(View drawerView) {
            startActivity(new Intent(MainActivity.this,gotoClz));
            mDrawerLayout.setDrawerListener(openDrawerListener);
        }

    }

    private DrawerLayout.DrawerListener notificationDrawerListener = new OtherItemDrawerListener(NotificationActivity.class);
    private DrawerLayout.DrawerListener settingDrawerListener = new OtherItemDrawerListener(SettingActivity.class);
    private DrawerLayout.DrawerListener aboutDrawerListener = new OtherItemDrawerListener(AboutActivity.class);

    // 其他菜单
    @OnClick({R.id.main_fab_new_topic, R.id.main_nav_layout_info, R.id.main_nav_btn_logout, R.id.main_nav_btn_theme_dark})
    public void onOtherItemClick(View view) {
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
                SettingShared.setEnableThemeDark(this,!enableThemeDark);
                ThemeUtils.recreateActivity(this);
                break;
        }
    }

    /**
     * 下拉刷新
     */

    @Override
    public void onRefresh() {
        final TabType tab = currentTab;
        L.d("刷新的tab为："+tab.getNameId());
        ApiClient.service.getTopicList(tab, 1, 20, true, new Callback<Result<List<Topic>>>() {
            @Override
            public void success(Result<List<Topic>> listResult, Response response) {
                if (currentTab == tab && listResult.getData()!= null){
                    topicList.clear();
                    topicList.addAll(listResult.getData());
                    notifyDataSetChanged();
                    mRefreshLayout.setRefreshing(false);
                    currentPage = 1;

                }
            }

            @Override
            public void failure(RetrofitError error) {
                if (currentTab == tab){
                    T.showShort(R.string.data_load_faild);
                    mRefreshLayout.setRefreshing(false);
                }
            }
        });
    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMore() {
        if (adapter.canLoadMore()){
            // 更新列表最后一个，显示出加载中
            adapter.setLoading(true);
            adapter.notifyItemChanged(adapter.getItemCount()-1);

            final TabType tab = currentTab;
            final int page = currentPage;
            ApiClient.service.getTopicList(tab, page + 1, 20, true, new Callback<Result<List<Topic>>>() {
                @Override
                public void success(Result<List<Topic>> result, Response response) {
                    if (currentTab == tab && currentPage == page){
                        if (result.getData().size() >  0){
                            topicList.addAll(result.getData());
                            adapter.setLoading(false);
                            adapter.notifyItemRangeInserted(topicList.size() - result.getData().size(),result.getData().size());
                            currentPage ++;
                        }else {
                            T.showShort(R.string.have_no_more_data);
                            adapter.setLoading(false);
                            adapter.notifyItemChanged(adapter.getItemCount()-1);
                        }
                    }
                }

                @Override
                public void failure(RetrofitError error) {
                    if (currentTab == tab && currentPage == page){
                        T.showShort(R.string.data_load_faild);
                        adapter.setLoading(false);
                        adapter.notifyItemChanged(adapter.getItemCount()-1);
                    }
                }
            });
        }

    }

    // 刷新列表数据
    private void notifyDataSetChanged(){
        if (topicList.size() < 20){
            adapter.setLoading(false);
        }
        adapter.notifyDataSetChanged();
        mIconNoData.setVisibility(topicList.size() == 0 ?View.VISIBLE:View.GONE);
    }

    /**
     * 显示需要登录对话框
     */
    private void showNeedLoginDialog(){
        DialogUtils.createAlertDialogBuilder(this)
                .setMessage(R.string.need_login_tip)
                .setPositiveButton(R.string.login, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // TODO 登录
                    }
                })
                .setNegativeButton(R.string.cancel,null)
                .show();
    }

    /***
     * 判断是否登录成功
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * 返回键监听
     */
    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)){
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else {
            long secondBackPressedTime = System.currentTimeMillis();
            if (secondBackPressedTime - firstBackPressedTime > 2000){
                T.showShort("再按一次退出");
                firstBackPressedTime = secondBackPressedTime;
            }else {
                super.onBackPressed();
            }
        }
    }
}
