package com.qw.library.widget.tab;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

import com.qw.library.R;
import com.qw.library.utils.TextUtil;

import java.util.ArrayList;


/**
 */
public class TabIndicator extends LinearLayout implements OnClickListener {
    private int mTabSize;
    private int mTabIndex = -1;
    private OnTabClickListener listener;
    private LayoutParams mTabParams;
    private final static int ID_PREFIX = 100000;
    private ColorStateList tabColor;

    public TabIndicator(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView(context, attrs);
    }


    public TabIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context, attrs);
    }

    private void initializeView(Context context, AttributeSet attrs) {
        setOrientation(LinearLayout.HORIZONTAL);
        MarginLayoutParams param = (MarginLayoutParams) new LayoutParams(0, LayoutParams.WRAP_CONTENT);
        mTabParams = new LayoutParams(param);
        mTabParams.weight = 1.0f;
        parseAttrs(context, attrs);

    }

    private void parseAttrs(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TabIndicator);
        if (a.hasValue(R.styleable.TabIndicator_label_color)) {
            this.tabColor = a.getColorStateList(R.styleable.TabIndicator_label_color);
        }
    }

    public void setOnTabClickListener(OnTabClickListener listener) {
        this.listener = listener;
    }

    public void initializeData(ArrayList<Tab> tabs) {
        if (!TextUtil.isValidate(tabs)) {
            throw new IllegalArgumentException("the tabs should not be 0");
        }
        mTabSize = tabs.size();
        TabView tab = null;
        removeAllViews();
        for (int i = 0; i < mTabSize; i++) {
            tab = new TabView(getContext());
            tab.setId(ID_PREFIX + i);
            tab.setOnClickListener(this);
            tab.initializeData(tabs.get(i));
            tab.setLabelColorStateList(tabColor);
            tab.notifyDataChanged();
            addView(tab, mTabParams);
        }
    }

    public void onDataChanged(int index, int number) {
        onDataChanged(index, number + "");
    }

    public void onDataChanged(int index, String number) {
        ((TabView) (findViewById(ID_PREFIX + index))).notifyDataChanged(number);
    }


    @Override
    public void onClick(View v) {
        int index = v.getId() - ID_PREFIX;
        if (listener != null && mTabIndex != index) {
            if (listener.onTabClick(v.getId() - ID_PREFIX)) {
                if (mTabIndex != index) {
                    v.setSelected(true);
                    if (mTabIndex != -1) {
                        View old = findViewById(ID_PREFIX + mTabIndex);
                        old.setSelected(false);
                    }
                    mTabIndex = index;
//				listener.onTabClick(v.getId() - ID_PREFIX);
                }
            }
        }
    }

    public interface OnTabClickListener {
        boolean onTabClick(int index);
    }

    public void setCurrentTab(int i) {
        if (i == mTabIndex) {
            return;
        }
        View view = findViewById(ID_PREFIX + i);
        onClick(view);
    }
}
