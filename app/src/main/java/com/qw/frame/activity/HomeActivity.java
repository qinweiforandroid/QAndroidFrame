package com.qw.frame.activity;

import com.qw.frame.R;
import com.qw.frame.support.BaseActivity;

/**
 * Created by qinwei on 2016/3/28 14:59
 * email:qinwei_it@163.com
 */
public class HomeActivity extends BaseActivity {
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initializeData() {
        setTitle("主页");
    }
}
