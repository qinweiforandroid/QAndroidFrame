package com.qw.library.widget.pulltorefresh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.qw.library.R;
import com.qw.library.widget.IFooterView;
import com.qw.library.widget.pulltorefresh.layout.ILayoutManager;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by qinwei on 2016/4/6 14:17
 * email:qinwei_it@163.com
 */
public class PullRecyclerViewPTR extends LinearLayout {
    private boolean isPullToRefhreshEnabled = true;
    private boolean isLoadMoreEnabled = false;
    private BaseListAdapter adapter;
    public PtrHTFrameLayout mPtrFrameLayout;
    public RecyclerView mRecyclerView;
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

    public PullRecyclerViewPTR(Context context) {
        super(context);
        initializeView();
    }

    public PullRecyclerViewPTR(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView();
    }

    public PullRecyclerViewPTR(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initializeView();
    }

    public void setAdapter(BaseListAdapter adapter) {
        this.adapter = adapter;
        layoutManager.setAdapter(adapter);
        mRecyclerView.setAdapter(adapter);
    }

    private void initializeView() {
        LayoutInflater.from(getContext()).inflate(R.layout.widget_pull_recycle_view_ptr, this);
        mPtrFrameLayout = (PtrHTFrameLayout) findViewById(R.id.mPtrFrameLayout);
        mPtrFrameLayout.setPtrHandler(new PtrDefaultHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                if (listener != null) {
                    mCurrentState = STATE_PULL_TO_REFRESH;
                    listener.onRefresh(State.PULL_TO_START);
                }
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                if (isPullToRefhreshEnabled) {
                    return super.checkCanDoRefresh(frame, content, header);
                } else {
                    return false;
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
                        setPullToRefreshEnabled(false);
                        listener.onRefresh(State.PULL_TO_END);
                    }
                }
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }


    public void setLayoutManager(ILayoutManager manager) {
        this.layoutManager = manager;
        mRecyclerView.setLayoutManager(manager.getLayoutManager());
        if (adapter != null) {
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
        return count < 2;
    }

    public void setRefreshing() {
        mPtrFrameLayout.autoRefresh();
    }

    public void onRefreshCompleted() {
        if (mCurrentState == STATE_PULL_TO_REFRESH) {
            mPtrFrameLayout.refreshComplete();
        } else if (mCurrentState == STATE_LOAD_MORE) {
            setPullToRefreshEnabled(true);
        }
        mCurrentState = STATE_IDLE;
    }

    public void setPullToRefreshEnabled(boolean isPullToRefreshEnabled) {
        this.isPullToRefhreshEnabled = isPullToRefreshEnabled;
//        mPtrFrameLayout.setPullToRefresh(isPullToRefreshEnabled);
    }

    public void setLoadMoreEnabled(boolean isLoadMoreEnabled) {
        this.isLoadMoreEnabled = isLoadMoreEnabled;
    }

    public void setOnPullRecyclerListener(OnPullRecyclerListener listener) {
        this.listener = listener;
    }
}
