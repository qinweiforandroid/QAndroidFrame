package com.qw.library.widget.pulltorefresh.layout;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;

import com.qw.library.widget.pulltorefresh.BaseListAdapter;

/**
 * Created by qinwei on 2016/4/6 15:07
 * email:qinwei_it@163.com
 */
public class MStaggeredGridLayoutManager extends StaggeredGridLayoutManager implements ILayoutManager{
    public MStaggeredGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MStaggeredGridLayoutManager(int spanCount, int orientation) {
        super(spanCount, orientation);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return this;
    }

    @Override
    public int findLastVisiblePosition() {
        int[] into=null;
        into=findLastVisibleItemPositions(into);
        return into[0];
    }

    @Override
    public void setAdapter(BaseListAdapter adapter) {
    }
}
