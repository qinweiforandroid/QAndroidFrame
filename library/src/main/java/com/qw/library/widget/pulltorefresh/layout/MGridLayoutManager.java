package com.qw.library.widget.pulltorefresh.layout;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.qw.library.widget.pulltorefresh.BaseListAdapter;

/**
 * Created by qinwei on 2016/4/6 15:09
 * email:qinwei_it@163.com
 */
public class MGridLayoutManager extends GridLayoutManager implements ILayoutManager {
    public MGridLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public MGridLayoutManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public MGridLayoutManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return this;
    }

    @Override
    public int findLastVisiblePosition() {
        return findLastVisibleItemPosition();
    }

    @Override
    public void setAdapter(final BaseListAdapter adapter) {
        setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                if (adapter.isLoadMoreFooter(position) || adapter.isGroupHeader(position)||adapter.isHeaderView(position)) {
                    return getSpanCount();
                }
                return 1;
            }
        });
    }
}
