package com.qw.library.widget.pulltorefresh;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by qinwei on 2016/4/6 13:36
 * email:qinwei_it@163.com
 */
public abstract class QBaseViewHolder extends RecyclerView.ViewHolder {
    public QBaseViewHolder(View itemView) {
        super(itemView);
        initializeView(itemView);
    }
    public abstract void initializeView(View v);
    public void initializeData(int position){}
}
