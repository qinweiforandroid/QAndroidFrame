package com.qw.frame.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

import com.qw.frame.R;
import com.qw.frame.core.BaseActivity;
import com.qw.frame.fragment.PageFragment;
import com.qw.frame.utils.Constants;
import com.qw.library.widget.tab.TabEntry;
import com.qw.library.widget.tab.TabLayout;

import java.util.ArrayList;


/**
 * Created by qinwei on 2016/3/18 12:38
 * email:qinwei_it@163.com
 */
public class HomeBottomActivity extends BaseActivity implements TabLayout.OnTabClickListener {
    private TabLayout mHomeIndicator;
    private ArrayList<TabEntry> tabs;
    private int currentIndex;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_home_tab);
    }

    @Override
    protected void initializeView() {
        mHomeIndicator = (TabLayout) findViewById(R.id.mHomeIndicator);
        mHomeIndicator.setOnTabClickListener(this);
    }

    @Override
    protected void initializeData(Bundle saveInstance) {
        tabs = new ArrayList<>();
        tabs.add(new TabEntry("首页", R.drawable.tab_inquiry_btn, PageFragment.class));
        tabs.add(new TabEntry("资讯", R.drawable.tab_casehistory_btn, PageFragment.class));
        tabs.add(new TabEntry("发现", R.drawable.tab_community_btn, PageFragment.class));
        tabs.add(new TabEntry("个人", R.drawable.tab_mine_btn, PageFragment.class));
        if (saveInstance != null) {
            currentIndex = saveInstance.getInt(Constants.KEY_CURRENT_TAB_INDEX);
            for (int i = 0; i < tabs.size(); i++) {
                if (getSupportFragmentManager().findFragmentByTag("" + i) != null)
                    getSupportFragmentManager().beginTransaction().hide(getSupportFragmentManager().findFragmentByTag("" + i)).commitAllowingStateLoss();
            }
        }
        mHomeIndicator.initializeData(tabs);
        switchTab(currentIndex);
    }


    public void switchTab(int index) {
        mHomeIndicator.setCurrentTab(index);
    }

    @Override
    public boolean onTabClick(int index) {
        FragmentManager fm = getSupportFragmentManager();
        try {
//            switch (index) {
//                case 1:
//                    Toast.makeText(getApplicationContext(), "登录", Toast.LENGTH_SHORT).show();
//                    return false;
//            }
            Fragment to = fm.findFragmentByTag("" + index);
            Fragment from = fm.findFragmentByTag("" + currentIndex);
            FragmentTransaction ft = fm.beginTransaction();
            if (to == null) {
                ft.add(R.id.mHomeContent, tabs.get(index).getFragmentClass().newInstance(), "" + index).commit();
            } else {
                if (to.isAdded()) {
                    ft.hide(from).show(to).commit();
                } else {
                    ft.hide(from).add(R.id.mHomeContent, tabs.get(index).getFragmentClass().newInstance(), "" + index).commit();
                }
            }
            currentIndex = index;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        setTitle(tabs.get(index).getLabel());
        return true;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.KEY_CURRENT_TAB_INDEX, currentIndex);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
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