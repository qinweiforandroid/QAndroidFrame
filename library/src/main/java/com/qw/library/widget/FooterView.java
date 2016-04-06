package com.qw.library.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qw.library.R;


public class FooterView extends LinearLayout implements IFooterView, OnClickListener {
    private ProgressBar mProgressBar;
    private TextView mFooterLabel;
    public State state = State.done;
    private OnFooterViewListener listener;

    public FooterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView(context);
    }

    public FooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    public FooterView(Context context) {
        super(context);
        initializeView(context);
    }


    private void initializeView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.widget_listview_refresh_footer, this);
        mProgressBar = (ProgressBar) findViewById(R.id.mProgressBar);
        mFooterLabel = (TextView) findViewById(R.id.mFooterLabel);
        this.setOnClickListener(this);
    }

    /**
     * update footerView UI
     *
     * @param status footerView mCurrentState
     */
    public void notifyDataChanged(State status) {
        this.state = status;
        setVisibility(View.VISIBLE);
        switch (status) {
            case done:
            case ing:
                mFooterLabel.setText("正在加载");
                mProgressBar.setVisibility(View.VISIBLE);
                break;
            case error:
                mFooterLabel.setText("点击重试");
                mProgressBar.setVisibility(View.GONE);
                break;
            case no_data:
                mProgressBar.setVisibility(View.GONE);
                mFooterLabel.setText("没有更多数据!");
                break;
            default:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (state) {
            case error:
                if (listener != null) {
                    notifyDataChanged(State.ing);
                    listener.onRetryLoadMore();
                }
                break;
            default:
                break;
        }
    }

    /**
     * 设置重试加载监听器
     *
     * @param listener 加载监听器
     */
    public void setOnFooterViewListener(OnFooterViewListener listener) {
        this.listener = listener;
    }
}
