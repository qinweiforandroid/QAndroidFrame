package com.qw.frame.core;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
    private View view;
    protected LoadingView mLoadingView;
    protected boolean isFirstLoad = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            initializeArguments(getArguments());
        }
    }

    /**
     * 初始化fragment的参数配置
     *
     * @param args 配置参数
     */
    protected void initializeArguments(Bundle args) {

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(getFragmentLayoutId(), container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        if (view.findViewById(R.id.mLoadingView) != null) {
            mLoadingView = (LoadingView) view.findViewById(R.id.mLoadingView);
            mLoadingView.setOnRetryListener(this);
            mLoadingView.notifyDataChanged(LoadingView.State.ing);
        }
        initializeView(view);
    }

    protected abstract int getFragmentLayoutId();

    protected abstract void initializeView(View v);

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isFirstLoad && isVisibleToUser) {
            lazyLoad();
            isFirstLoad = false;
        }
        Trace.d(this.getClass().getSimpleName(), isVisibleToUser + "");
    }

    protected void lazyLoad() {
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public View getCurrentView() {
        return view;
    }

    @Override
    public void onRetry() {
        mLoadingView.notifyDataChanged(LoadingView.State.ing);
    }
}
