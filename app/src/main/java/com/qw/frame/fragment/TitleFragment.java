package com.qw.frame.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qw.frame.R;
import com.qw.frame.core.BaseFragment;

/**
 * Created by qinwei on 16/5/20 上午11:19
 */
public class TitleFragment extends BaseFragment implements View.OnClickListener {

    private Button mButton;
    private TextView mTextView;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_title;
    }

    @Override
    protected void initializeView(View v) {
        mTextView = (TextView) v.findViewById(R.id.mTextView);
        mButton = (Button) v.findViewById(R.id.mButton);
        mButton.setOnClickListener(this);
    }

    @Override
    protected void initializeData() {
        super.initializeData();
        mTextView.setText("hello fragment");
    }

    @Override
    public void onClick(View v) {
        mTextView.setText(mTextView.getText().toString() + " ME TOO");
    }
}
