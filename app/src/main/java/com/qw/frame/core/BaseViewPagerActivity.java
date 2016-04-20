package com.qw.frame.core;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.qw.frame.R;

import java.util.ArrayList;

/**
 * Created by qinwei on 2016/4/6 18:21
 * email:qinwei_it@163.com
 */
public abstract class BaseViewPagerActivity<T> extends BaseActivity implements ViewPager.OnPageChangeListener {
    protected DataPageAdapter adapter;
    protected ArrayList<T> modules = new ArrayList<>();
    protected ViewPager generalViewPager;

    @Override
    protected void initializeView() {
        super.initializeView();
        generalViewPager = (ViewPager) findViewById(R.id.generalViewPager);
        generalViewPager.addOnPageChangeListener(this);
        generalViewPager.setOffscreenPageLimit(4);
        adapter = new DataPageAdapter(getSupportFragmentManager());
        generalViewPager.setAdapter(adapter);
    }

    public void notifyDataSetChanged() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(int position) {
    }

    class DataPageAdapter extends FragmentStatePagerAdapter {

        public DataPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return getFragmentAtPosition(position);
        }

        @Override
        public int getCount() {
            return modules.size();
        }
    }

    /**
     * 获取所有底部tab图片资源
     *
     * @param data
     * @return
     */
    public abstract Fragment getFragmentAtPosition(int position);
}
