package com.qw.frame.support;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qw.frame.R;
import com.qw.library.adapter.QBaseRecyclerViewHolder;
import com.qw.library.widget.FooterView;
import com.qw.library.widget.PullRecyclerView;

import java.util.ArrayList;


/**
 * Created by qinwei on 2015/10/26 13:51
 * email:qinwei_it@163.com
 */
public abstract class BaseRecyclerRefreshViewActivity extends BaseActivity implements PullRecyclerView.OnPullRecyclerListener {
    private final int VIEW_TYPE_FOOTER = -2;
    private final int VIEW_TYPE_HEADER = -1;
    protected ArrayList<Object> modules = new ArrayList<>();
    protected DataAdapter adapter;
    protected PullRecyclerView mPullRecyclerView;
    protected FooterView.State footerState = FooterView.State.ing;

    @Override
    protected void initializeView() {
        super.initializeView();
        mPullRecyclerView = (PullRecyclerView) findViewById(R.id.mPullRecyclerView);
        mPullRecyclerView.setOnPullRecyclerListener(this);
        mPullRecyclerView.setColorSchemeColors(R.color.red, R.color.red, R.color.pink, R.color.black);
        mPullRecyclerView.setHasFixedSize(true);
        mPullRecyclerView.setPullToRefreshEnabled(true);
        mPullRecyclerView.setLoadMoreEnabled(isCanLoadMore());
        mPullRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new DataAdapter();
        mPullRecyclerView.mRecyclerView.setAdapter(adapter);
    }

    public void setLayoutManager(RecyclerView.LayoutManager manager) {
        mPullRecyclerView.setLayoutManager(manager);
    }

    @Override
    public final void onRefresh(int state) {
        if (state == PullRecyclerView.STATE_LOAD_MORE) {
            onLoadMore();
        } else {
            mPullRecyclerView.mSwipeRefreshLayout.setEnabled(false);
            onRefresh();
        }
    }

    public void onLoadMore() {

    }

    public void onRefresh() {

    }

    public void notifyFooterViewDataChanged(FooterView.State state) {
        this.footerState = state;
        switch (state) {
            case done:
            case ing:
                mPullRecyclerView.setState(PullRecyclerView.STATE_IDLE);
                break;
            case error:
            case no_data:
                mPullRecyclerView.setState(PullRecyclerView.STATE_LOAD_MORE);
                break;
            default:
                break;
        }
        adapter.notifyItemChanged(adapter.getItemCount() - 1);
    }

    public void onRefreshCompleted() {
        mPullRecyclerView.setRefreshing(false);
        mPullRecyclerView.setState(PullRecyclerView.STATE_IDLE);
    }

    public int getHeaderViewId() {
        return -1;
    }

    public void initializeHeaderView(View view) {

    }

    public class DataAdapter extends RecyclerView.Adapter {

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (VIEW_TYPE_FOOTER == viewType) {
                View view = LayoutInflater.from(BaseRecyclerRefreshViewActivity.this).inflate(R.layout.pull_recycler_footer, parent, false);
                return new FooterViewHolder(view);
            } else if (viewType == VIEW_TYPE_HEADER) {
                return new HeaderViewHolder(LayoutInflater.from(BaseRecyclerRefreshViewActivity.this).inflate(getHeaderViewId(), parent, false));
            }
            return BaseRecyclerRefreshViewActivity.this.onCreateViewHolder(parent, viewType);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            QBaseRecyclerViewHolder h = (QBaseRecyclerViewHolder) holder;
            if (isContainerHeader() && position != 0) {
                if (!(isCanLoadMore() && position + 1 == getItemCount()))
                    position--;
            }
            h.initializeData(position);
        }

        @Override
        public int getItemViewType(int position) {
            if (getItemCount() == position + 1 && isCanLoadMore()) {
                return VIEW_TYPE_FOOTER;
            } else if (isContainerHeader() && position == 0) {
                return VIEW_TYPE_HEADER;
            }
            return getAdapterItemViewType(position);
        }

        @Override
        public int getItemCount() {
            int count = modules.size();
            if (isContainerHeader()) {
                count++;
            }
            if (isCanLoadMore()) {
                count++;
            }
            return count;
        }

        class HeaderViewHolder extends QBaseRecyclerViewHolder {
            public HeaderViewHolder(View itemView) {
                super(itemView);
                setIsRecyclable(false);
                setFullSpan(itemView);
                initializeHeaderView(itemView);
            }

            @Override
            public void initializeData(int position) {
            }
        }

        class FooterViewHolder extends QBaseRecyclerViewHolder implements FooterView.OnFooterViewListener {
            private final FooterView footerView;

            public FooterViewHolder(View itemView) {
                super(itemView);
                setIsRecyclable(false);
                setFullSpan(itemView);
                footerView = (FooterView) itemView.findViewById(R.id.mFooterView);
                footerView.setOnFooterViewListener(this);
            }

            @Override
            public void initializeData(int position) {
                footerView.notifyDataChanged(footerState);
            }

            @Override
            public void onRetryLoadMore() {
                footerState = FooterView.State.ing;
                onRefresh(PullRecyclerView.STATE_LOAD_MORE);
            }
        }

        public void setFullSpan(View itemView) {
            if (mPullRecyclerView.mRecyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
                StaggeredGridLayoutManager.LayoutParams layoutParams = new StaggeredGridLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                layoutParams.setFullSpan(true);
                itemView.setLayoutParams(layoutParams);
            }
        }
    }

    public int getAdapterItemViewType(int position) {
        return 0;
    }


    public abstract QBaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType);

    public boolean isContainerHeader() {
        return false;
    }

    protected boolean isCanLoadMore() {
        return false;
    }
}
