package com.qw.frame.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.qw.frame.R;
import com.qw.frame.core.BaseStickyHeaderListActivity;
import com.qw.library.widget.pulltorefresh.QBaseViewHolder;

import java.util.Arrays;

/**
 * Created by qinwei on 2016/4/25 16:25
 * email:qinwei_it@163.com
 */
public class StickHeaderListActivity extends BaseStickyHeaderListActivity<String> {
    private String[] getDummyDataSet() {
        return getResources().getStringArray(R.array.animals);
    }

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_pullrecyclerview_sticky_header);
    }

    @Override
    protected void initializeView() {
        super.initializeView();

    }

    @Override
    protected void onItemClick(View view, int position) {
        Toast.makeText(StickHeaderListActivity.this, modules.get(position), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initializeData(Bundle saveInstance) {
        mPullRecycler.setPullToRefreshEnabled(false);
        modules.addAll(Arrays.asList(getDummyDataSet()));
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onBindHeaderViewHolder(RecyclerView.ViewHolder holder, int position) {
        TextView textView = (TextView) holder.itemView;
        textView.setText(String.valueOf(modules.get(position).charAt(0)));
    }

    @Override
    protected RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_header, parent, false);
        return new RecyclerView.ViewHolder(view) {
        };
    }

    @Override
    protected long getHeaderId(int position) {
//        position<0则不添加header
        //      position 与position+1数据进行比对 如果是一组则返回值一致  不一致 内部则增加一个header
//        if (position == 0) {
//            return -1;
//        } else {
            return modules.get(position).charAt(0);
//        }
    }

    @Override
    protected QBaseViewHolder onCreateAdapterView(LayoutInflater from, ViewGroup parent, int viewType) {
        QBaseViewHolder holder = new PullRecyclerViewHolder(from.inflate(R.layout.activity_pullrecyclerview_sticky_header_item, parent, false), this);
        return holder;
    }

    public class PullRecyclerViewHolder extends QBaseViewHolder<String> {
        private TextView mStickyItemLabel;

        public PullRecyclerViewHolder(View itemView, Context context) {
            super(itemView, context);
        }

        @Override
        public void initializeView(View v) {
            mStickyItemLabel = (TextView) v.findViewById(R.id.mStickyItemLabel);
        }
        @Override
        public void initializeData(String s, int position) {
            mStickyItemLabel.setText(s);
        }

    }

}
