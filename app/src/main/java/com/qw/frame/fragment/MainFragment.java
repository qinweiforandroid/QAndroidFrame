package com.qw.frame.fragment;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qw.frame.R;
import com.qw.frame.core.BaseFragment;
import com.qw.library.widget.password.PasswordView;

/**
 * Created by qinwei on 2016/3/29 18:52
 * email:qinwei_it@163.com
 */
public class MainFragment extends BaseFragment implements PasswordView.PasswordViewInputListener {
    private TextView mContentLabel;
    private PasswordView mPasswordView;

    @Override
    protected int getFragmentLayoutId() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initializeView(View v) {
        mContentLabel = (TextView) v.findViewById(R.id.mContentLabel);
        mPasswordView = (PasswordView) v.findViewById(R.id.mPasswordView);
        mPasswordView.setOnPasswordViewInputListener(this);
    }

    @Override
    protected void initializeData() {
        mContentLabel.setText("MainFragment");
    }

    @Override
    public void onPasswordViewInputComplete(String password) {
        Toast.makeText(getActivity(), "输入完成" + password, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPasswordViewInputNotComplete() {

    }
}
