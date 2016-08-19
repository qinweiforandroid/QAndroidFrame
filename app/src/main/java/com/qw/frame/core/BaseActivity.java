package com.qw.frame.core;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qw.library.AppStatusTracker;
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
    protected Toolbar toolbar;
    protected LoadingView mLoadingView;
    protected TextView mToolBarTitleLabel;

    @Override
    protected void onCreate(Bundle saveInstance) {
        super.onCreate(saveInstance);
        if (AppStatusTracker.getInstance().isAppKilled()) {
            protectApp();
        } else {
            setContentView();
            initializeToolbar();
            initializeView();
            initializeData(saveInstance);
        }
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
    protected abstract void initializeView();

    private void initializeToolbar() {
        if (findViewById(R.id.toolbar) != null) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);
            if (hasBackIcon()) {
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
        if (!TextUtils.isEmpty(getIntent().getStringExtra(Constants.KEY_TITLE)))
            setTitle(getIntent().getStringExtra(Constants.KEY_TITLE));
    }

    /**
     * 3. 初始化ui数据
     */
    protected abstract void initializeData(Bundle saveInstance);


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finishActivity();
        }
        return super.onOptionsItemSelected(item);
    }

    public void finishActivity() {
        finish();
    }

    /**
     * 是否有回退功能
     *
     * @return
     */
    protected boolean hasBackIcon() {
        return true;
    }

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

    public void setContentView(int layoutId) {
        setContentView(layoutId, true);
    }

    protected void setContentView(int layoutId, boolean isContainerTitle) {
        if (isContainerTitle) {
            LinearLayout root = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.layout_content, null);
            LayoutInflater.from(this).inflate(layoutId, root);
            super.setContentView(root);
        } else {
            super.setContentView(layoutId);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
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
}

