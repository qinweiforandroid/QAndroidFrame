package com.qw.library.widget.pulltorefresh.layout;

import android.support.v7.widget.RecyclerView;

import com.qw.library.widget.pulltorefresh.BaseListAdapter;

/**
 * Created by qinwei on 2016/4/6 14:22
 * email:qinwei_it@163.com
 */
public interface ILayoutManager {
    RecyclerView.LayoutManager getLayoutManager();

    int findLastVisiblePosition();

    void setAdapter(BaseListAdapter adapter);
}
