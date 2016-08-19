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
    protected ViewPager mViewPager;

    @Override
    protected void initializeView() {
        mViewPager = (ViewPager) findViewById(R.id.generalViewPager);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.setOffscreenPageLimit(4);
        adapter = new DataPageAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
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
        @Override
        public CharSequence getPageTitle(int position) {
            return getPageTitleAtPosition(position);
        }
    }

    protected abstract CharSequence getPageTitleAtPosition(int position);

    /**
     * 获取所有底部tab图片资源
     *
     * @param position
     * @return
     */
    public abstract Fragment getFragmentAtPosition(int position);
}
