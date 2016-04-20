package com.qw.frame.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qw.frame.R;
import com.qw.frame.core.BaseActivity;
import com.qw.library.widget.indicator.CirclePageIndicator;
import com.qw.library.widget.loop.LoopViewPager;

/**
 * Created by qinwei on 2016/4/11 14:24
 * email:qinwei_it@163.com
 */
public class LoopViewPagerActivity extends BaseActivity {
    private LoopViewPager mLoopViewPager;
    private DataPagerAdapter adapter;
    private CirclePageIndicator mCirclePageIndicator;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_viewpager_loop);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        mLoopViewPager = (LoopViewPager) findViewById(R.id.mLoopViewPager);
        mCirclePageIndicator = (CirclePageIndicator) findViewById(R.id.mCirclePageIndicator);
    }

    @Override
    protected void initializeData(Bundle saveInstance) {
        adapter = new DataPagerAdapter();
        mLoopViewPager.setOffscreenPageLimit(2);
        mLoopViewPager.setAdapter(adapter);
        mCirclePageIndicator.setViewPager(mLoopViewPager);
        mLoopViewPager.startAutoScroll();
    }

    class DataPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView label = new TextView(container.getContext());
            label.setTextSize(22);
            label.setText("page:" + position);
            container.addView(label, ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT);
            return label; // 返回该view对象，作为key
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        mLoopViewPager.stopAutoScroll();
    }

    @Override
    public void onResume() {
        super.onResume();
        mLoopViewPager.startAutoScroll();
    }
}
