package com.qw.frame.activity;

import android.os.Bundle;

import com.qw.frame.R;
import com.qw.frame.core.BaseActivity;
import com.qw.frame.core.BaseFragment;
import com.qw.frame.utils.Constants;

/**
 * Created by qinwei on 16/5/20 上午11:08
 */
public class FragmentContentActivity extends BaseActivity {
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_fragment_container);
    }

    @Override
    protected void initializeView() {

    }

    @Override
    protected void initializeData(Bundle saveInstance) {
        Bundle args = getIntent().getExtras();
        Class<? extends BaseFragment> clazz = (Class<? extends BaseFragment>) args.getSerializable(Constants.KEY_FRAGMENT_CLASS);
        if (saveInstance == null) {
            try {
                getSupportFragmentManager().beginTransaction().replace(R.id.generalContent, clazz.newInstance()).commitAllowingStateLoss();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
