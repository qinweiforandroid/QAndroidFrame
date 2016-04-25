package com.qw.frame.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.qw.frame.R;
import com.qw.frame.core.BaseListActivity;
import com.qw.frame.entity.Meizhi;
import com.qw.frame.holder.PullRecyclerViewHolder;
import com.qw.frame.utils.GankIoCallback;
import com.qw.library.net.AppException;
import com.qw.library.net.Request;
import com.qw.library.net.RequestManager;
import com.qw.library.utils.Trace;
import com.qw.library.widget.IFooterView;
import com.qw.library.widget.pulltorefresh.PullRecyclerView;
import com.qw.library.widget.pulltorefresh.QBaseViewHolder;
import com.qw.library.widget.pulltorefresh.layout.MGridLayoutManager;
import com.qw.library.widget.pulltorefresh.layout.MLinearLayoutManager;
import com.qw.library.widget.pulltorefresh.layout.MStaggeredGridLayoutManager;

import java.util.ArrayList;


/**
 * Created by qinwei on 2015/10/26 14:57
 * email:qinwei_it@163.com
 */
public class PullRecyclerActivity extends BaseListActivity<Meizhi> {
    int pageNum = 1;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_pullrecyclerview);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
    }

    @Override
    protected void initializeData(Bundle saveInstance) {
        setTitle("RecyclerRefreshView");
        mPullRecycler.setRefreshing();
    }

    public void loadDataFromServer(final boolean loadMore) {
        Request request = new Request("http://gank.io/api/data/福利/20/" + pageNum);
        request.setCallback(new GankIoCallback<ArrayList<Meizhi>>() {
            @Override
            public void onSuccess(ArrayList<Meizhi> result) {
                Trace.e("onSuccess:" + result.size());
                if (loadMore) {
                    if (result.size() < 20) {
                        adapter.notifyLoadMoreStateChanged(IFooterView.State.no_data);
                    } else {
                        adapter.notifyLoadMoreStateChanged(IFooterView.State.done);
                    }
                } else {
                    modules.clear();
                }
                pageNum++;
                modules.addAll(result);
                mPullRecycler.onRefreshCompleted();
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(AppException e) {
                mPullRecycler.onRefreshCompleted();
                if (loadMore) {
                    adapter.notifyLoadMoreStateChanged(IFooterView.State.error);
                }
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }

    @Override
    public void onRefresh(PullRecyclerView.State state) {
        if (state == PullRecyclerView.State.PULL_TO_START) {
            pageNum = 1;
            loadDataFromServer(false);
        } else {
            loadDataFromServer(true);
        }
    }

    @Override
    protected QBaseViewHolder onCreateAdapterView(LayoutInflater from, ViewGroup parent, int viewType) {
        QBaseViewHolder holder = new PullRecyclerViewHolder(LayoutInflater.from(this).inflate(R.layout.activity_pullrecyclerview_item, null));
        return holder;
    }


    @Override
    public int getHeaderLayoutId() {
        return R.layout.layout_header;
    }

    @Override
    protected void initializeHeaderView(View v) {
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_recyclerview_swiperefresh_layout_mode, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_list:
                setLayoutManager(new MLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                break;
            case R.id.action_gridview:
                setLayoutManager(new MGridLayoutManager(this, 2));
                break;
            case R.id.action_staggered:
                setLayoutManager(new MStaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
                break;
            case R.id.action_add:
                break;
            case R.id.action_remove:
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected boolean isLoadMoreEnabled() {
        return true;
    }

    @Override
    public boolean isPullToRefreshEnabled() {
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RequestManager.getInstance().cancelRequest(this.toString());
    }
}
