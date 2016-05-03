package com.qw.frame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.qw.frame.R;
import com.qw.frame.core.BaseListActivity;
import com.qw.frame.entity.ClassEntity;
import com.qw.frame.holder.ClassEntityViewHolder;
import com.qw.library.widget.pulltorefresh.QBaseViewHolder;
import com.qw.library.widget.pulltorefresh.layout.MGridLayoutManager;

import java.util.ArrayList;

/**
 * Created by qinwei on 2016/3/28 14:59
 * email:qinwei_it@163.com
 */
public class HomeActivity extends BaseListActivity<ClassEntity> {
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_home);
    }

    @Override
    protected void initializeData(Bundle saveInstance) {
        setTitle("主页");
        mPullRecycler.setPullToRefreshEnabled(false);
        ArrayList<ClassEntity> datas = ClassEntity.getDatas();
        modules.addAll(datas);
        adapter.notifyDataSetChanged();
        setLayoutManager(new MGridLayoutManager(this, 2));
    }

    @Override
    protected QBaseViewHolder onCreateAdapterView(LayoutInflater from, ViewGroup parent, int viewType) {
        return new ClassEntityViewHolder(from.inflate(R.layout.activity_home_item, parent,false), this);
    }

    @Override
    protected boolean hasBackIcon() {
        return false;
    }

    @Override
    public void protectApp() {
        startActivity(new Intent(this, SplashActivity.class));
        finish();
    }
}
