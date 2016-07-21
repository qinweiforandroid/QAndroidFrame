package com.qw.library.widget.tab;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qw.library.R;


public class TabItem extends LinearLayout {

    private ImageView mTabIconImg;
    private TextView mTabLabel;
    private ColorStateList labelColorStateList;
    private TabEntry mTab;

    public TabItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initializeView(context);
    }

    public TabItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initializeView(context);
    }

    public TabItem(Context context) {
        super(context);
        initializeView(context);
    }

    private void initializeView(Context context) {
        LayoutInflater.from(context).inflate(R.layout.widget_tab, this);
        mTabIconImg = (ImageView) findViewById(R.id.mTabIconImg);
        mTabLabel = (TextView) findViewById(R.id.mTabLabel);
    }

    public void initializeData(TabEntry tab) {
        this.mTab = tab;

    }

    public void notifyDataChanged(int number) {
        notifyDataChanged(number + "");
    }

    public void notifyDataChanged(String number) {
        BadgeView badgeView = new BadgeView(getContext());
        badgeView.setTargetView(mTabIconImg);
        badgeView.setBadgeCount(number);
    }

    public void notifyDataChanged() {
        mTabIconImg.setImageResource(mTab.getIconRes());
        mTabLabel.setText(mTab.getLabel());
        mTabLabel.setTextColor(labelColorStateList);
    }

    public void setLabelColorStateList(ColorStateList labelColorStateList) {
        this.labelColorStateList = labelColorStateList;
    }

    public ColorStateList getLabelColorStateList() {
        return labelColorStateList;
    }
}
