package com.qw.frame.core;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.qw.frame.R;
import com.qw.library.widget.pulltorefresh.sticky.RecyclerItemClickListener;
import com.qw.library.widget.pulltorefresh.BaseListAdapter;
import com.qw.library.widget.pulltorefresh.PullRecyclerView;
import com.qw.library.widget.pulltorefresh.QBaseViewHolder;
import com.qw.library.widget.pulltorefresh.layout.ILayoutManager;
import com.qw.library.widget.pulltorefresh.layout.MLinearLayoutManager;
import com.qw.library.widget.pulltorefresh.sticky.StickyRecyclerHeadersAdapter;
import com.qw.library.widget.pulltorefresh.sticky.StickyRecyclerHeadersDecoration;
import com.qw.library.widget.pulltorefresh.sticky.StickyRecyclerHeadersTouchListener;

import java.util.ArrayList;


/**
 * Created by qinwei on 2015/10/26 13:51
 * email:qinwei_it@163.com
 */
public abstract class BaseStickyHeaderListActivity<T> extends BaseActivity implements PullRecyclerView.OnPullRecyclerListener {
    protected ArrayList<T> modules = new ArrayList<>();
    protected PullRecyclerView mPullRecycler;
    protected ListAdapter adapter;

    @Override
    protected void initializeView() {
        super.initializeView();
        mPullRecycler = (PullRecyclerView) findViewById(R.id.mPullRecyclerView);
        mPullRecycler.setOnPullRecyclerListener(this);
        mPullRecycler.setPullToRefreshEnabled(isPullToRefreshEnabled());
        mPullRecycler.setLoadMoreEnabled(isLoadMoreEnabled());
        mPullRecycler.setLayoutManager(getLayoutManager());
        mPullRecycler.setOnPullRecyclerListener(this);
        adapter = new ListAdapter(modules);
        adapter.isHeaderViewShow = getHeaderLayoutId() != -1;
        mPullRecycler.setAdapter(adapter);

        // Add the sticky headers decoration
        final StickyRecyclerHeadersDecoration headersDecor = new StickyRecyclerHeadersDecoration(adapter);
        mPullRecycler.addItemDecoration(headersDecor);

        // Add decoration for dividers between list items
//        mPullRecycler.addItemDecoration(new DividerDecoration(this));

        // Add touch listeners
        StickyRecyclerHeadersTouchListener touchListener =
                new StickyRecyclerHeadersTouchListener(mPullRecycler.mRecyclerView, headersDecor);
        touchListener.setOnHeaderClickListener(
                new StickyRecyclerHeadersTouchListener.OnHeaderClickListener() {
                    @Override
                    public void onHeaderClick(View header, int position, long headerId) {
                        Toast.makeText(BaseStickyHeaderListActivity.this, "Header position: " + position + ", id: " + headerId,
                                Toast.LENGTH_SHORT).show();
                    }
                });
        mPullRecycler.mRecyclerView.addOnItemTouchListener(touchListener);
        mPullRecycler.mRecyclerView.addOnItemTouchListener(new RecyclerItemClickListener(this, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                BaseStickyHeaderListActivity.this.onItemClick(view,position);
            }
        }));
        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                headersDecor.invalidateHeaders();
            }
        });
    }


    public void setLayoutManager(ILayoutManager manager) {
        mPullRecycler.setLayoutManager(manager);
    }

    @Override
    public void onRefresh(PullRecyclerView.State state) {

    }

    public boolean isPullToRefreshEnabled() {
        return false;
    }

    public int getHeaderLayoutId() {
        return -1;
    }

    protected void initializeHeaderView(View v) {

    }

    public class ListAdapter extends BaseListAdapter<T> implements StickyRecyclerHeadersAdapter<RecyclerView.ViewHolder> {
        public ListAdapter(ArrayList<T> modules) {
            super(modules);
        }
        @Override
        protected QBaseViewHolder onCreateHeaderView(LayoutInflater from, ViewGroup parent) {
            QBaseViewHolder holder = new HeaderHolder(from.inflate(getHeaderLayoutId(), parent, false));
            return holder;
        }

        @Override
        protected int getItemAdapterCount() {
            return modules.size();
        }

        @Override
        public QBaseViewHolder onCreateAdapterView(ViewGroup parent, int viewType) {
            return BaseStickyHeaderListActivity.this.onCreateAdapterView(LayoutInflater.from(BaseStickyHeaderListActivity.this), parent, viewType);
        }

        @Override
        protected void onRetryLoadMore() {
            onRefresh(PullRecyclerView.State.PULL_TO_END);
        }

        @Override
        public long getHeaderId(int position) {
            return BaseStickyHeaderListActivity.this.getHeaderId(position);
        }

        @Override
        public RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
            return BaseStickyHeaderListActivity.this.onCreateHeaderViewHolder(parent);
        }

        @Override
        public void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
            BaseStickyHeaderListActivity.this.onBindHeaderViewHolder(holder, position);
        }

        @Override
        protected int getAdapterItemViewType(int position) {
            return BaseStickyHeaderListActivity.this.getAdapterItemViewType(position);
        }
    }

    protected abstract void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position);

    protected abstract RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent);

    protected abstract long getHeaderId(int position);

    private class HeaderHolder extends QBaseViewHolder {

        public HeaderHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initializeView(View v) {
            setIsRecyclable(false);
            initializeHeaderView(v);
        }
    }

    protected abstract QBaseViewHolder onCreateAdapterView(LayoutInflater from, ViewGroup parent, int viewType);

    protected int getAdapterItemViewType(int position) {
        return 0;
    }

    protected boolean isLoadMoreEnabled() {
        return false;
    }

    public ILayoutManager getLayoutManager() {
        return new MLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    }
    protected abstract void onItemClick(View view, int position);
}
