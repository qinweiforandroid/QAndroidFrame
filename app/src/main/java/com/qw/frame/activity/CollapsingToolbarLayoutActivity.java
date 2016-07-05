package com.qw.frame.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.view.WindowManager;
import android.widget.ImageView;

import com.qw.frame.R;
import com.qw.frame.core.BaseViewPagerActivity;
import com.qw.frame.fragment.ListFragment;
import com.qw.frame.fragment.PageFragment;
import com.qw.library.utils.ImageDisplay;

/**
 * Created by qinwei on 16/7/5 上午11:02
 */
public class CollapsingToolbarLayoutActivity extends BaseViewPagerActivity<String> {
    private TabLayout mTabLayout;
    private ImageView mImageView;

    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_collapsing_toolbar_layout, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }

    @Override
    protected void initializeView() {
        super.initializeView();
        mImageView = (ImageView) findViewById(R.id.mImageView);
        mTabLayout = (TabLayout) findViewById(R.id.mTabLayout);
        String url = "http://b.zol-img.com.cn/desk/bizhi/image/6/960x600/1438075090731.jpg";
        ImageDisplay.getInstance().displayImage(url, mImageView);
        setTitle("雨过天晴");
    }

    @Override
    protected void initializeData(Bundle saveInstance) {
        modules.add("分享");
        modules.add("收藏");
        modules.add("关注");
        modules.add("关注者");
        notifyDataSetChanged();
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected CharSequence getPageTitleAtPosition(int position) {
        return modules.get(position);
    }

    @Override
    public Fragment getFragmentAtPosition(int position) {
        Fragment fragment = PageFragment.getInstance(modules.get(position));
        switch (position) {
            case 0:
                break;
            case 1:
                fragment=new ListFragment();
                break;
            case 2:
                break;
            case 3:
                break;
            default:
                break;
        }

        return fragment;
    }


}
