package com.qw.frame.fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.qw.frame.R;
import com.qw.frame.core.BaseFragment;

/**
 * Created by qinwei on 2016/3/29 18:52
 * email:qinwei_it@163.com
 */
public class PageFragment extends BaseFragment {
    private TextView mContentLabel;
    private String pageTitle;

    public static BaseFragment getInstance(String pageTitle) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putString("pageTitle", pageTitle);
        args.putBoolean(KEY_IS_BIND_VIEWPAGER, true);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_content;
    }

    @Override
    protected void initializeArguments(Bundle args) {
        super.initializeArguments(args);
        pageTitle = args.getString("pageTitle");
    }

    @Override
    protected void initializeView(View v) {
        mContentLabel = (TextView) v.findViewById(R.id.mContentLabel);
        mContentLabel.setText("" + System.currentTimeMillis());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_loading_view, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void initializeData() {
        mContentLabel.setText(pageTitle);
    }
}
