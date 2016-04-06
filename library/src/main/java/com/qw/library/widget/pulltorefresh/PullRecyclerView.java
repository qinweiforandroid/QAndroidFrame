package com.qw.library.widget.pulltorefresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.qw.library.R;
import com.qw.library.widget.IFooterView;
import com.qw.library.widget.pulltorefresh.layout.ILayoutManager;

/**
 * Created by qinwei on 2016/4/6 14:17
 * email:qinwei_it@163.com
 */
public class PullRecyclerView extends LinearLayout  {
    private boolean isPullToRefhreshEnabled = true;
    private boolean isLoadMoreEnabled = false;
    private BaseListAdapter adapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private ILayoutManager layoutManager;
    public static final int STATE_IDLE = 0;
    public static final int STATE_LOAD_MORE = 1;
    public static final int STATE_PULL_TO_REFRESH = 2;
    public int mCurrentState = 0;
    private OnPullRecyclerListener listener;

    public interface OnPullRecyclerListener {
        void onRefresh(State state);
    }

    public enum State {
        PULL_TO_START, PULL_TO_END
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

    public void setAdapter(BaseListAdapter adapter) {
        this.adapter = adapter;
        layoutManager.setAdapter(adapter);
        mRecyclerView.setAdapter(adapter);
    }

    private void initializeView() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_pull_recycle_view, this);
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.mSwipeRefreshLayout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (listener != null) {
                    doRefresh();
                }
            }
        });
        mRecyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (isLoadMoreEnabled && mCurrentState == STATE_IDLE && adapter.isCanLoadMore() && checkedIsNeedLoadMore()) {
                    if (listener != null) {
                        mCurrentState = STATE_LOAD_MORE;
                        adapter.notifyLoadMoreStateChanged(IFooterView.State.ing);
                        listener.onRefresh(State.PULL_TO_END);
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    private void doRefresh() {
        mCurrentState = STATE_PULL_TO_REFRESH;
        listener.onRefresh(State.PULL_TO_START);
    }


    public void setLayoutManager(ILayoutManager manager) {
        this.layoutManager = manager;
        mRecyclerView.setLayoutManager(manager.getLayoutManager());
        if(adapter!=null){
            layoutManager.setAdapter(adapter);
        }
    }

    public void addItemDecoration(RecyclerView.ItemDecoration decor) {
        mRecyclerView.addItemDecoration(decor);

    }

    public void setItemAnimator(RecyclerView.ItemAnimator animator) {
        mRecyclerView.setItemAnimator(animator);
    }

    public boolean checkedIsNeedLoadMore() {
        int lastVisiblePosition = layoutManager.findLastVisiblePosition();
        int count = mRecyclerView.getLayoutManager().getItemCount() - lastVisiblePosition;
        return count < 5;
    }

    public void setRefreshing() {
        mSwipeRefreshLayout.setRefreshing(true);
        doRefresh();
    }

    public void onRefreshCompleted() {
        if (mCurrentState == STATE_PULL_TO_REFRESH) {
            adapter.notifyLoadMoreStateChanged(IFooterView.State.done);
            mSwipeRefreshLayout.setRefreshing(false);
        }
        mCurrentState=STATE_IDLE;
    }

    public void setPullToRefreshEnabled(boolean isPullToRefreshEnabled) {
        this.isPullToRefhreshEnabled = isPullToRefreshEnabled;
        mSwipeRefreshLayout.setEnabled(isPullToRefreshEnabled);
    }

    public void setLoadMoreEnabled(boolean isLoadMoreEnabled) {
        this.isLoadMoreEnabled = isLoadMoreEnabled;
    }

    public void setOnPullRecyclerListener(OnPullRecyclerListener listener) {
        this.listener = listener;
    }
}
