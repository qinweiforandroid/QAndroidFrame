package com.qw.frame.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;

import com.qw.frame.R;
import com.qw.frame.core.BaseViewPagerActivity;
import com.qw.frame.fragment.MainFragment;
import com.qw.frame.fragment.PageFragment;
import com.qw.library.utils.Trace;
import com.qw.library.widget.tab.Tab;
import com.qw.library.widget.tab.TabIndicator;

/**
 * Created by qinwei on 2016/4/6 18:21
 * email:qinwei_it@163.com
 */
public class HomeViewPagerActivity extends BaseViewPagerActivity<Tab> implements TabIndicator.OnTabClickListener {
    private TabIndicator generalTabIndicator;
    private TabLayout mTabLayout;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_home_tab_viewpager);
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        generalTabIndicator = (TabIndicator) findViewById(R.id.generalTabIndicator);
        generalTabIndicator.setOnTabClickListener(this);
    }

    @Override
    protected void initializeData(Bundle saveInstance) {
        modules.add(new Tab("首页", R.drawable.tab_inquiry_btn, MainFragment.class));
        modules.add(new Tab("资讯", R.drawable.tab_casehistory_btn, PageFragment.class));
        modules.add(new Tab("发现", R.drawable.tab_community_btn, PageFragment.class));
        modules.add(new Tab("个人", R.drawable.tab_mine_btn, PageFragment.class));
        notifyDataSetChanged();
        generalTabIndicator.initializeData(modules);
        generalTabIndicator.setCurrentTab(0);
        mTabLayout.setupWithViewPager(generalViewPager);
    }

    @Override
    public void onPageSelected(int position) {
        Trace.e("onPageSelected " + position);
        generalTabIndicator.setCurrentTab(position);
    }

    @Override
    protected CharSequence getPageTitleAtPosition(int position) {
        return modules.get(position).getLabel();
    }


    @Override
    public boolean onTabClick(int index) {
        Trace.e("onTabClick " + index);
        generalViewPager.setCurrentItem(index);
        return true;
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
