package com.qw.library.widget.pulltorefresh;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qw.library.R;
import com.qw.library.widget.FooterView;
import com.qw.library.widget.IFooterView;
import com.qw.library.widget.OnFooterViewListener;

/**
 * Created by qinwei on 2016/4/6 11:46
 * email:qinwei_it@163.com
 */
public abstract class BaseListAdapter extends RecyclerView.Adapter {
    public static final int VIEW_TYPE_LOAD_MORE_FOOTER = 1000;
    private static final int VIEW_TYPE_HEADER = 2000;
    public boolean isLoadMoreShown;
    public boolean isHeaderViewShow;
    protected IFooterView.State state = IFooterView.State.done;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        QBaseViewHolder holder;
        if (viewType == VIEW_TYPE_LOAD_MORE_FOOTER) {
            holder = onCreateLoadMoreFooter(LayoutInflater.from(parent.getContext()), parent);
        }else if(viewType==VIEW_TYPE_HEADER){
            holder=onCreateHeaderView(LayoutInflater.from(parent.getContext()), parent);
        } else {
            holder = onCreateAdapterView(parent, viewType);
        }
        return holder;
    }

    protected abstract QBaseViewHolder onCreateHeaderView(LayoutInflater from, ViewGroup parent);

    protected QBaseViewHolder onCreateLoadMoreFooter(LayoutInflater from, ViewGroup parent) {

        return new FooterViewHolder(from.inflate(R.layout.pull_refresh_end, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (isLoadMoreFooter(position)||isHeaderView(position)) {
            if (holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
                StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
                params.setFullSpan(true);
            }
        }
        if(isHeaderViewShow){
            position--;
        }
        QBaseViewHolder h = (QBaseViewHolder) holder;
        h.initializeData(position);
    }

    @Override
    public int getItemCount() {
        return getItemAdapterCount() + (isLoadMoreShown ? 1 : 0)+ (isHeaderViewShow ? 1 : 0);
    }

    @Override
    public int getItemViewType(int position) {
        if (isLoadMoreFooter(position)) {
            return VIEW_TYPE_LOAD_MORE_FOOTER;
        }else if(isHeaderView(position)){
            return VIEW_TYPE_HEADER;
        }
        return getAdapterItemViewType(position);
    }
    protected abstract int getItemAdapterCount();

    public abstract QBaseViewHolder onCreateAdapterView(ViewGroup parent, int viewType);

    protected abstract int getAdapterItemViewType(int position);

    protected abstract void onRetryLoadMore();


    class FooterViewHolder extends QBaseViewHolder implements OnFooterViewListener {
        private FooterView footerView;

        public FooterViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initializeView(View v) {
            setIsRecyclable(false);
            footerView = (FooterView) v.findViewById(R.id.mFooterView);
            footerView.setOnFooterViewListener(this);
        }

        @Override
        public void initializeData(int position) {
            footerView.notifyDataChanged(state);
        }

        @Override
        public void onRetryLoadMore() {
            state = IFooterView.State.ing;
            BaseListAdapter.this.onRetryLoadMore();
        }
    }

    public void notifyLoadMoreStateChanged(IFooterView.State state) {
//        isLoadMoreShown=true;
        this.state = state;

        switch (state) {
            case ing:
            case error:
            case no_data:
                isLoadMoreShown = true;
                notifyItemInserted(getItemCount());
//                notifyItemChanged(getItemCount() - 1);
                break;
            case done:
                isLoadMoreShown = false;
                notifyItemRemoved(getItemCount());
                break;
            default:
                break;
        }

    }

    public boolean isLoadMoreFooter(int position) {
        return isLoadMoreShown && position == getItemCount() - 1;
    }
    public  boolean isHeaderView(int position){
        return isHeaderViewShow&&position==0;
    }
    public boolean isGroupHeader(int position) {
        return false;
    }

    public boolean isCanLoadMore() {
        switch (state) {
            case done:
                return true;
            default:
                return false;
        }
    }


}
