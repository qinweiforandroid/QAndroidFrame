package com.qw.frame.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import com.qw.frame.R;
import com.qw.frame.core.BaseActivity;
import com.qw.frame.fragment.MainFragment;
import com.qw.frame.fragment.PageFragment;
import com.qw.library.utils.Trace;
import com.qw.library.widget.tab.Tab;
import com.qw.library.widget.tab.TabIndicator;

import java.util.ArrayList;

/**
 * Created by qinwei on 2016/4/6 18:21
 * email:qinwei_it@163.com
 */
public class HomeViewPagerActivity extends BaseActivity implements ViewPager.OnPageChangeListener, TabIndicator.OnTabClickListener {
    private HomePageAdapter adapter;
    private ArrayList<Tab> tabs = new ArrayList<>();

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_home_tab_viewpager);
    }

    private ViewPager generalViewPager;
    private TabIndicator generalTabIndicator;

    @Override
    protected void initializeView() {
        super.initializeView();
        generalTabIndicator = (TabIndicator) findViewById(R.id.generalTabIndicator);
        generalViewPager = (ViewPager) findViewById(R.id.generalViewPager);
        generalViewPager.addOnPageChangeListener(this);
        generalViewPager.setOffscreenPageLimit(4);
        generalTabIndicator.setOnTabClickListener(this);
    }

    @Override
    protected void initializeData() {
        tabs.add(new Tab("首页", R.drawable.tab_inquiry_btn, MainFragment.class));
        tabs.add(new Tab("资讯", R.drawable.tab_casehistory_btn, PageFragment.class));
        tabs.add(new Tab("发现", R.drawable.tab_community_btn, PageFragment.class));
        tabs.add(new Tab("个人", R.drawable.tab_mine_btn, PageFragment.class));
        adapter = new HomePageAdapter(getSupportFragmentManager());
        generalViewPager.setAdapter(adapter);
        generalTabIndicator.initializeData(tabs);
//        generalViewPager.setCurrentItem(0);
        generalTabIndicator.setCurrentTab(0);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onPageSelected(int position) {
        Trace.e("onPageSelected " + position);
        generalTabIndicator.setCurrentTab(position);
    }

    @Override
    public boolean onTabClick(int index) {
        Trace.e("onTabClick " + index);
        generalViewPager.setCurrentItem(index);
        return true;
    }


    class HomePageAdapter extends FragmentStatePagerAdapter {

        public HomePageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return getFragmentAtPosition(position);
        }

        @Override
        public int getCount() {
            return tabs.size();
        }
    }

    /**
     * 获取所有底部tab图片资源
     *
     * @return 底部tab图片资源
     */
    public Fragment getFragmentAtPosition(int position) {
        switch (position) {
            case 0:
                return new MainFragment();
            case 1:
                return PageFragment.getInstance("资讯");
            case 2:
                return PageFragment.getInstance("发现");
            case 3:
                return PageFragment.getInstance("个人");
            default:
                break;
        }
        return null;
    }
}
