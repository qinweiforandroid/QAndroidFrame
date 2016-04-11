package com.qw.frame.fragment;

import android.view.View;
import android.widget.TextView;

import com.qw.frame.R;
import com.qw.frame.core.BaseFragment;

/**
 * Created by qinwei on 2016/3/29 18:52
 * email:qinwei_it@163.com
 */
public class MainFragment extends BaseFragment {
    private TextView mContentLabel;
    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initializeView(View v) {
        mContentLabel = (TextView) v.findViewById(R.id.mContentLabel);
    }

    @Override
    protected void initializeData() {
        mContentLabel.setText("MainFragment");
    }
}
