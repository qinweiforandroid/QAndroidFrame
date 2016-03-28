package com.qw.frame.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.qw.frame.MyApplication;
import com.qw.frame.R;
import com.qw.library.config.AppConfig;


public class SplashActivity extends Activity {
    public static final int STATE_LOGINED = 100;
    public static final int STATE_NO_LOGIN = 200;
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case STATE_NO_LOGIN:
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    break;
            }
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        MyApplication.getInstance().setAppState(MyApplication.APP_STATE_STARTED);
        if (AppConfig.getInstance().isFirstOpen()) {
            Log.e("wei", "app is first start");
            AppConfig.getInstance().saveCurrentVersionCode();
        } else {
            Log.e("wei", "app started");
        }
//		TelephonyManager tm = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
//		Toast.makeText(this, tm.getDeviceId(), 1000).show();
        if (MyApplication.getLoginUser() == null) {
            mHandler.sendEmptyMessageDelayed(STATE_NO_LOGIN, 2000);

        } else {
            mHandler.sendEmptyMessageDelayed(STATE_LOGINED, 2000);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
