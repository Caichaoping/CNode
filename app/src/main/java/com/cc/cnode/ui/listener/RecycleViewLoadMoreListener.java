package com.cc.cnode.ui.listener;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * 注释：
 * 作者：菠菜 on 2016/4/7 17:00
 * 邮箱：971859818@qq.com
 */
public class RecycleViewLoadMoreListener extends RecyclerView.OnScrollListener {

    private LinearLayoutManager linearLayoutManager;
    private OnLoadMoreListener onLoadMoreListener;
    private int limit;

    public RecycleViewLoadMoreListener(LinearLayoutManager linearLayoutManager, OnLoadMoreListener onLoadMoreListener, int limit) {
        super();
        this.linearLayoutManager = linearLayoutManager;
        this.onLoadMoreListener = onLoadMoreListener;
        this.limit = limit;
    }

    @Override
    public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        // 向下滑动，判断最后一个item是不是显示中,如果是加载更多
        if (linearLayoutManager.getItemCount() >= limit && linearLayoutManager.findLastVisibleItemPosition() == linearLayoutManager.getItemCount()-1){
            onLoadMoreListener.onLoadMore();
        }
    }

    public interface OnLoadMoreListener {
        void onLoadMore();
    }
}
