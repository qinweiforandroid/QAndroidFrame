package com.qw.frame.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.qw.frame.R;
import com.qw.frame.core.BaseActivity;

/**
 * Created by qinwei on 16/8/11 下午3:45
 */
public class ApkHelperActivity extends BaseActivity implements View.OnClickListener {
    @Override
    protected void setContentView() {
        setContentView(R.layout.activity_apk_helpper, true);
    }

    @Override
    protected void initializeView() {
        findViewById(R.id.mApkHelperOpenAllMarket).setOnClickListener(this);
        findViewById(R.id.mApkHelperOpenTXMarket).setOnClickListener(this);
    }

    @Override
    protected void initializeData(Bundle saveInstance) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mApkHelperOpenAllMarket:
                //TODO implement
                openAllMarket();
                break;
            case R.id.mApkHelperOpenTXMarket:
                //TODO implement
                openTXMarket();
                break;
        }
    }

    private void openTXMarket() {
        try {
            /**
             * 通过app包名搜索App
             http://market.android.com/search?q=pname:<app的包名>
             或者
             market://search?q=pname:<app的包名>

             通过开发者名称搜索App

             http://market.android.com/search?q=pub:<开发者名称>
             或者
             market://search?q=pub:<开发者名称>

             通过关键词搜索App

             http://market.android.com/search?q=<关键词>
             或者
             market://search?q=<关键词>
             */
            String mAddress = "market://details?id=" + getPackageName();
            Intent marketIntent = new Intent("android.intent.action.VIEW");
            marketIntent.setData(Uri.parse(mAddress));
            startActivity(marketIntent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ApkHelperActivity.this, "手机没有安装应用市场！", Toast.LENGTH_SHORT).show();
        }
    }

    private void openAllMarket() {
        try {
            Intent intent = new Intent("android.intent.action.MAIN");
            intent.addCategory("android.intent.category.APP_MARKET");
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(ApkHelperActivity.this, "手机没有安装应用市场！", Toast.LENGTH_SHORT).show();
        }
    }
}
