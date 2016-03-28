package com.qw.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;

import com.qw.library.R;


/**
 * emptyView解决了请求网络数据时ui显示的三种状态
 * 分别为加载中，加载失败，无数据
 * email: qinwei_it@163.com
 *
 * @author qinwei create by 2015/10/28
 */
public class LoadingView extends FrameLayout implements OnClickListener {

    private View empty;
    private View error;
    private View loading;
    private State state;
    private OnRetryListener listener;

    public interface OnRetryListener {
        void onRetry();
    }

    public enum State {
        ing, error, done, empty
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView(context);
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    public LoadingView(Context context) {
        super(context);
        initializeView(context);
    }

    private void initializeView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.widget_loading_view, this);
        empty = findViewById(R.id.empty);
        loading = findViewById(R.id.loading);
        error = findViewById(R.id.error);
        setOnClickListener(this);
        notifyDataChanged(State.ing);
    }

    public void notifyDataChanged(State state) {
        this.state = state;
        switch (state) {
            case ing:
                setVisibility(View.VISIBLE);
                loading.setVisibility(View.VISIBLE);
                empty.setVisibility(View.GONE);
                error.setVisibility(View.GONE);
                break;
            case empty:
                setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
                empty.setVisibility(View.VISIBLE);
                error.setVisibility(View.GONE);
                break;
            case error:
                setVisibility(View.VISIBLE);
                loading.setVisibility(View.GONE);
                empty.setVisibility(View.GONE);
                error.setVisibility(View.VISIBLE);
                break;
            case done:
                setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    public void setOnRetryListener(OnRetryListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null && state == State.error) {
            listener.onRetry();
        }
    }
}
