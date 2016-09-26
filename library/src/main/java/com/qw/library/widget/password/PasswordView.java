package com.qw.library.widget.password;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.qw.library.R;
import com.qw.library.utils.Trace;

import java.util.ArrayList;


/**
 * 支付密码view 6位
 *
 * @author 秦伟
 * @version 1.0
 * @created 创建时间: 2015-5-13 下午4:55:22
 */
public class PasswordView extends LinearLayout implements OnClickListener, TextWatcher, OnEditorActionListener {
    private ArrayList<EditText> passwords = new ArrayList<EditText>();
    private LinearLayout mPassswordContainer;
    private EditText mEdt;
    private InputMethodManager inputMethodManager;
    private PasswordViewInputListener listener;

    public interface PasswordViewInputListener {
        /**
         * 密码输入完成
         *
         * @param password
         */
        void onPasswordViewInputComplete(String password);

        /**
         * 密码输入为完成
         */
        void onPasswordViewInputNotComplete();
    }

    public PasswordView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView(context);
    }

    public PasswordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    public PasswordView(Context context) {
        super(context);
        initializeView(context);
    }

    public void setOnPasswordViewInputListener(PasswordViewInputListener listener) {
        this.listener = listener;
    }

    private void initializeView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.widget_password_view, this);
        findViewById(R.id.mLabel).setOnClickListener(this);
        mPassswordContainer = (LinearLayout) findViewById(R.id.mPassswordContainer);
        mEdt = (EditText) findViewById(R.id.mEdt);
        inputMethodManager = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        mEdt.addTextChangedListener(this);
        mEdt.setOnEditorActionListener(this);
        mPassswordContainer.setOnClickListener(this);
        for (int i = 0; i < 6; i++) {
            EditText view = (EditText) LayoutInflater.from(context).inflate(R.layout.widget_password_item, mPassswordContainer, false);
            passwords.add(view);
            mPassswordContainer.addView(view);
        }
    }

    @Override
    public void onClick(View v) {
        Trace.d(getClass().getSimpleName());
        mEdt.requestFocus();
        inputMethodManager.showSoftInput(mEdt, 0);
    }

    private String currentStr = "";

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        currentStr = s.toString();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String changedStr = null;
        if (s.toString().length() > currentStr.length()) {
            changedStr = s.toString().substring(currentStr.length());
//            Trace.d("录入:" + s.toString().substring(currentStr.length()));
            passwords.get(s.toString().length() - 1).setText(changedStr);
        } else {
            passwords.get(s.toString().length()).setText(null);
//            Trace.d("删除:" + currentStr.toString().substring(s.length()));
            changedStr = currentStr.toString().substring(s.length());
        }
        if (listener != null) {
//            Trace.d("listener:" + s.toString().length());
            if (s.toString().length() == 6) {
                listener.onPasswordViewInputComplete(getPassword());
            } else {
                listener.onPasswordViewInputNotComplete();
            }
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_NEXT) {// 这个问题卡了很久了
            hideSoftInput();
            return true;
        }
        return false;
    }

    private void hideSoftInput() {
        String password = "";
        for (int i = 0; i < passwords.size(); i++) {
            password += passwords.get(i).getText().toString();
        }
        Trace.d(password);
        inputMethodManager.hideSoftInputFromWindow(mEdt.getWindowToken(), 0);
    }

    public String getPassword() {
        String password = "";
        for (int i = 0; i < passwords.size(); i++) {
            password += passwords.get(i).getText().toString();
        }
        return password;
    }

}
