package com.qw.library.widget;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.qw.library.R;


/**
 * Created by qinwei on 2016/3/22 11:06
 * email:qinwei_it@163.com
 */
public class PullRecyclerView extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener {
    public SwipeRefreshLayout mSwipeRefreshLayout;
    public RecyclerView mRecyclerView;
    private boolean isPullToRefhreshEnabled = true;
    private boolean isLoadMoreEnabled = false;
    private OnPullRecyclerListener listener;
    private RecyclerView.LayoutManager layoutManager;
    public static final int STATE_IDLE = 0;
    public static final int STATE_LOAD_MORE = 1;
    public static final int STATE_PULL_TO_REFRESH = 2;
    public int state = 0;

    public interface OnPullRecyclerListener {
        void onRefresh(int state);
    }

    public PullRecyclerView(Context context) {
        super(context);
        initializeView();
    }


    public PullRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView();
    }

    public PullRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView();
    }

    private void initializeView() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_pull_recycle_view, this);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.mSwipeRefreshLayout);
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.addOnScrollListener(new PullRecyclerViewOnScrollListener());
    }


    class PullRecyclerViewOnScrollListener extends RecyclerView.OnScrollListener {
        public int lastVisiblePosition;

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
            if (manager instanceof LinearLayoutManager) {
                lastVisiblePosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            } else if (manager instanceof GridLayoutManager) {
                lastVisiblePosition = ((GridLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            } else if (manager instanceof StaggeredGridLayoutManager) {
                lastVisiblePosition = ((StaggeredGridLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPositions(null)[0];
            }
            super.onScrolled(recyclerView, dx, dy);
        }

        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            if (isLoadMoreEnabled && mRecyclerView.getAdapter().getItemCount() > 0) {
                if (state == STATE_IDLE && newState == RecyclerView.SCROLL_STATE_IDLE && lastVisiblePosition + 1 == mRecyclerView.getAdapter().getItemCount()) {
                    state = STATE_LOAD_MORE;
                    if (listener != null) {
                        listener.onRefresh(state);
                    }
                }
            }
            super.onScrollStateChanged(recyclerView, newState);
        }
    }


    @Override
    public void onRefresh() {
        if (listener != null) {
            state = STATE_PULL_TO_REFRESH;
            listener.onRefresh(STATE_PULL_TO_REFRESH);
        }
    }

    public void setRefreshing(boolean refreshing) {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public void setState(int state) {
        this.state = state;
    }

    public void setPullToRefreshEnabled(boolean enabled) {
        isPullToRefhreshEnabled = enabled;
        mSwipeRefreshLayout.setEnabled(enabled);
    }

    public void setLoadMoreEnabled(boolean enabled) {
        isLoadMoreEnabled = enabled;
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
        mRecyclerView.setLayoutManager(layoutManager);
    }

    public void setOnPullRecyclerListener(OnPullRecyclerListener listener) {
        this.listener = listener;
    }

    public void setColorSchemeColors(int... colors) {
        mSwipeRefreshLayout.setColorSchemeColors(colors);
    }

    public void setHasFixedSize(boolean hasFixedSize) {
        mRecyclerView.setHasFixedSize(hasFixedSize);
    }
}