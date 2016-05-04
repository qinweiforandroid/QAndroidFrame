package com.qw.library;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import com.qw.library.utils.Trace;

/**
 * Created by qinwei on 2016/5/4 21:15
 * email:qinwei_it@163.com
 */
public class AppStatusTracker implements Application.ActivityLifecycleCallbacks {
    public static final int MAX_UN_LINE_TIME = 5 * 1000 * 60;
    public static final int APP_STATE_INIT = 0;//app 初始化/被强杀
    public static final int APP_STATE_STARTED = 1;//app 开启
    public static final int APP_STATE_LOGINED = 2;//app 登陆
    private static AppStatusTracker mInstance;
    private Application application;

    private int app_state;
    private boolean isForeground;//应用是否在前台 true代表前台，false代表后台
    private int activityCount;

    private AppStatusTracker(Application application) {
        this.application = application;
        app_state = APP_STATE_INIT;
        application.registerActivityLifecycleCallbacks(this);
    }

    public static void init(Application application) {
        mInstance = new AppStatusTracker(application);
    }

    public static AppStatusTracker getInstance() {
        return mInstance;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

    }

    @Override
    public void onActivityStarted(Activity activity) {
        activityCount++;
        Trace.d(activity.getClass().getSimpleName() + ":onActivityStarted");
    }

    @Override
    public void onActivityResumed(Activity activity) {
        isForeground = true;
        Trace.d(activity.getClass().getSimpleName() + ":onActivityResumed");
    }

    @Override
    public void onActivityPaused(Activity activity) {
        Trace.d(activity.getClass().getSimpleName() + ":onActivityPaused");
    }

    @Override
    public void onActivityStopped(Activity activity) {
        activityCount--;
        Trace.d(activity.getClass().getSimpleName() + ":onActivityStopped，" + activityCount);
        if (activityCount == 0) {
            isForeground = false;
        }
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }


    public boolean isForeground() {
        return isForeground;
    }

    public int getAppState() {
        return app_state;
    }

    public boolean isAppKilled() {
        return app_state == APP_STATE_INIT;
    }

    public void setAppState(int state) {
        this.app_state = state;
    }

}
