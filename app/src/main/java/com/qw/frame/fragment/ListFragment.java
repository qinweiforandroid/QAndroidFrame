package com.qw.frame.fragment;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qw.frame.R;
import com.qw.frame.core.BaseListFragment;
import com.qw.library.widget.pulltorefresh.QBaseViewHolder;

/**
 * Created by qinwei on 16/7/5 下午4:39
 */
public class ListFragment extends BaseListFragment<String> {


    @Override
    protected int getFragmentLayoutId() {
        return R.layout.activity_pullrecyclerview;
    }

    @Override
    protected void initializeData() {
        for (int i = 0; i < 20; i++) {
            modules.add("" + System.currentTimeMillis());
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    protected QBaseViewHolder onCreateAdapterView(LayoutInflater from, ViewGroup parent, int viewType) {
        return new Holder(from.inflate(R.layout.layout_item,parent,false));
    }

    class Holder extends QBaseViewHolder<String> {

        private TextView mItemLabel;

        protected Holder(View itemView) {
            super(itemView);
        }

        @Override
        public void initializeView(View v) {
            mItemLabel = (TextView) v.findViewById(R.id.mItemLabel);
        }

        @Override
        public void initializeData(String module) {
            mItemLabel.setText(module);
        }
    }
}
