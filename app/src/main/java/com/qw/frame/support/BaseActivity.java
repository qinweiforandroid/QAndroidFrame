package com.qw.frame.support;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.qw.frame.MyApplication;
import com.qw.frame.R;
import com.qw.frame.activity.HomeActivity;
import com.qw.frame.utils.Constants;
import com.qw.library.widget.LoadingView;


/**
 * 结构化activity的代码
 * 方法调用顺序为setContentView()->initializeView()->recoveryState(saveInstance)-> initializeData();
 */
public abstract class BaseActivity extends AppCompatActivity implements LoadingView.OnRetryListener {
    protected String TAG = this.getClass().getSimpleName();
    protected LoadingView mLoadingView;

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        if (!MyApplication.getInstance().isAppKilled()) {
            setContentView();
            initializeView();
            initializeData();
            if (saveInstance != null) {
                recoveryState(saveInstance);
            }
        } else {
            protectApp();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void protectApp() {
        Log.e(TAG, "protectApp:class=" + this.getClass().getSimpleName());
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra(Constants.KEY_PROTECT_APP, true);
        startActivity(intent);
        finish();
    }

    /**
     * 1. 设置布局
     */
    protected abstract void setContentView();

    /**
     * 2. 初始化布局
     */
    protected void initializeView() {
        if (findViewById(R.id.toolbar) != null) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            if (isCanBack()) {
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
            if (isCenter()) {
                mToolBarTitleLabel = (TextView) findViewById(R.id.mToolBarTitleLabel);
            }
        }

        if (findViewById(R.id.mLoadingView) != null) {
            mLoadingView = (LoadingView) findViewById(R.id.mLoadingView);
            mLoadingView.setOnRetryListener(this);
            mLoadingView.notifyDataChanged(LoadingView.State.ing);
        }
        setTitle(getIntent().getStringExtra(Constants.KEY_TITLE));
    }

    /**
     * 界面被系统强杀 数据状态恢复
     *
     * @param saveInstance 状态数据
     */
    protected void recoveryState(Bundle saveInstance) {

    }

    /**
     * 3. 初始化ui数据
     */
    protected abstract void initializeData();

    protected TextView mToolBarTitleLabel;
    protected Toolbar toolbar;


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home && isCanFinish()) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 是否有回退功能
     *
     * @return
     */
    protected boolean isCanBack() {
        return true;
    }

    /**
     * 是否可以关闭当前界面
     *
     * @return
     */
    protected boolean isCanFinish() {
        return true;
    }

    /**
     * 标题是否居中
     *
     * @return
     */
    protected boolean isCenter() {
        return false;
    }

    @Override
    public void onRetry() {

    }

    @Override
    public void setTitle(CharSequence title) {
        if (mToolBarTitleLabel != null && isCenter()) {
            mToolBarTitleLabel.setText(title);
            super.setTitle("");
        } else {
            super.setTitle(title);
        }
    }
}

