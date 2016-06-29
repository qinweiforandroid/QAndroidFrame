package com.qw.frame.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qw.frame.R;
import com.qw.frame.entity.Meizhi;
import com.qw.library.utils.ImageDisplay;
import com.qw.library.widget.pulltorefresh.QBaseViewHolder;

/**
 * Created by qinwei on 2016/4/25 15:02
 * email:qinwei_it@163.com
 */
public class PullRecyclerViewHolder extends QBaseViewHolder<Meizhi> {
    private ImageView mHomeItemIconImg;
    private TextView mHomeItemTitleLabel;

    public PullRecyclerViewHolder(View view) {
        super(view);
    }

    @Override
    public void initializeView(View v) {
        mHomeItemIconImg = (ImageView) v.findViewById(R.id.mHomeItemIconImg);
        mHomeItemTitleLabel = (TextView) v.findViewById(R.id.mHomeItemTitleLabel);
    }

    @Override
    public void initializeData(Meizhi meizhi) {
        ImageDisplay.getInstance().displayImage(meizhi.getUrl(), mHomeItemIconImg);
        mHomeItemTitleLabel.setText(meizhi.getDesc());
    }
}
