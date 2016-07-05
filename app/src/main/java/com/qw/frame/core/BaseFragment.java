package com.qw.frame.core;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qw.frame.R;
import com.qw.library.utils.Trace;
import com.qw.library.widget.LoadingView;


/**
 * fragment简单的封装
 *
 * @author 秦伟
 * @version 1.0
 * @created 创建时间: 2015-8-22 下午7:07:47
 */
public abstract class BaseFragment extends Fragment implements LoadingView.OnRetryListener {
    private  final String TAG=getClass().getSimpleName();
    public static final String KEY_IS_BIND_VIEWPAGER = "key_is_bind_viewpager";
    protected LoadingView mLoadingView;
    private boolean isFirstLoad = true;
    private boolean isOnViewCreated = false;
    private boolean isBindViewPager;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        trace("onAttach");
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        isFirstLoad=true;
        trace("onCreate "+(savedInstanceState==null));
        if (getArguments() != null)
            initializeArguments(getArguments());
    }

    /**
     * 初始化fragment的参数配置
     *
     * @param args 配置参数
     */
    protected void initializeArguments(Bundle args) {
        if (args != null) {
            isBindViewPager = args.getBoolean(KEY_IS_BIND_VIEWPAGER, false);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        trace("onStart");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        trace("onCreateView");
        return inflater.inflate(getFragmentLayoutId(), container, false);
    }

    protected abstract int getFragmentLayoutId();

    protected abstract void initializeView(View v);

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        trace("onViewCreated");
        isOnViewCreated = true;
        if (view.findViewById(R.id.mLoadingView) != null) {
            mLoadingView = (LoadingView) view.findViewById(R.id.mLoadingView);
            mLoadingView.setOnRetryListener(this);
            mLoadingView.notifyDataChanged(LoadingView.State.ing);
        }
        initializeView(view);
        if (!isBindViewPager) {
            initializeData();
        }
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        trace("setUserVisibleHint " + isVisibleToUser);
        if (isFirstLoad && isVisibleToUser && isOnViewCreated) {
            trace("lazyLoad start call initializeData()");
            initializeData();
            isFirstLoad = false;
        }
    }

    protected void initializeData() {
        trace("initializeData");
    }

    @Override
    public void onResume() {
        super.onResume();
        trace("onResume");
    }

    @Override
    public void onPause() {
        super.onPause();
        trace("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        trace("onStop");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        trace("onDetach");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        trace("onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        trace("onDestroy");
    }

    private void trace(String msg){
//        Trace.d(TAG+":"+msg);
    }
    @Override
    public void onRetry() {
        mLoadingView.notifyDataChanged(LoadingView.State.ing);
    }
}
