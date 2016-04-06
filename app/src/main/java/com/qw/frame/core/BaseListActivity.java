package com.qw.frame.core;

import android.support.v7.widget.LinearLayoutManager;
import android.view.ViewGroup;

import com.qw.frame.R;
import com.qw.library.widget.pulltorefresh.BaseListAdapter;
import com.qw.library.widget.pulltorefresh.layout.ILayoutManager;
import com.qw.library.widget.pulltorefresh.PullRecyclerView;
import com.qw.library.widget.pulltorefresh.QBaseViewHolder;
import com.qw.library.widget.pulltorefresh.layout.MLinearLayoutManager;

import java.util.ArrayList;


/**
 * Created by qinwei on 2015/10/26 13:51
 * email:qinwei_it@163.com
 */
public abstract class BaseListActivity extends BaseActivity implements PullRecyclerView.OnPullRecyclerListener {
    protected ArrayList<Object> modules = new ArrayList<>();
    protected PullRecyclerView mPullRecycler;
    protected BaseListAdapter adapter;

    @Override
    protected void initializeView() {
        super.initializeView();
        mPullRecycler = (PullRecyclerView) findViewById(R.id.mPullRecyclerView);
        mPullRecycler.setOnPullRecyclerListener(this);
        mPullRecycler.setPullToRefreshEnabled(isPullToRefreshEnabled());
        mPullRecycler.setLoadMoreEnabled(isLoadMoreEnabled());
        mPullRecycler.setLayoutManager(getLayoutManager());
        mPullRecycler.setOnPullRecyclerListener(this);
        adapter = new ListAdapter();
        mPullRecycler.setAdapter(adapter);
    }
    public void setLayoutManager(ILayoutManager manager){
        mPullRecycler.setLayoutManager(manager);
    }

    @Override
    public void onRefresh(PullRecyclerView.State state) {

    }

    public boolean isPullToRefreshEnabled() {
        return false;
    }

    class ListAdapter extends BaseListAdapter {
        @Override
        protected int getItemAdapterCount() {
            return modules.size();
        }

        @Override
        public QBaseViewHolder onCreateAdapterView(ViewGroup parent, int viewType) {
            return BaseListActivity.this.onCreateAdapterView(parent, viewType);
        }

        @Override
        protected int getAdapterItemViewType(int position) {
            return BaseListActivity.this.getAdapterItemViewType(position);
        }

        @Override
        protected void onRetryLoadMore() {
            onRefresh(PullRecyclerView.State.PULL_TO_END);
        }
    }

    protected abstract QBaseViewHolder onCreateAdapterView(ViewGroup parent, int viewType);

    protected int getAdapterItemViewType(int position) {
        return 0;
    }
    protected boolean isLoadMoreEnabled() {
        return false;
    }
    public ILayoutManager getLayoutManager() {
        return new MLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    }
}
