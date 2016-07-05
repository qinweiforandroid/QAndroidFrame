package com.qw.frame;

import android.app.Application;

import com.qw.frame.utils.GlideDisplay;
import com.qw.frame.utils.UniversalImageloaderDisplay;
import com.qw.library.AppStatusTracker;
import com.qw.library.config.AppConfig;
import com.qw.library.utils.ImageDisplay;
import com.qw.library.utils.Trace;


public class MyApplication extends Application {
    private static MyApplication mInstance;


    public static Object getLoginUser() {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        AppStatusTracker.init(this);
        initializeImageloader();
        initializeDataTask();
        AppConfig config = AppConfig.getInstance();
        config.isDevelopment = true;
        config.init(getApplicationContext());
        Trace.model = config.isDevelopment;
    }


    private void initializeDataTask() {
    }

    public void initializeImageloader() {
        ImageDisplay.getInstance().init(new GlideDisplay(getApplicationContext()));
    }

    public static MyApplication getInstance() {
        return mInstance;
    }
}
