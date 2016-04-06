package com.qw.library.widget.pulltorefresh.layout;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.qw.library.widget.pulltorefresh.BaseListAdapter;

/**
 * Created by qinwei on 2016/4/6 14:26
 * email:qinwei_it@163.com
 */
public class MLinearLayoutManager extends LinearLayoutManager implements ILayoutManager {
    public MLinearLayoutManager(Context context) {
        super(context);
    }

    public MLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public MLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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
    public void setAdapter(BaseListAdapter adapter) {

    }
}
