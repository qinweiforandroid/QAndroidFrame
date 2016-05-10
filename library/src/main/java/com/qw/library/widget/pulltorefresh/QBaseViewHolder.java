package com.qw.library.widget.pulltorefresh;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by qinwei on 2016/4/6 13:36
 * email:qinwei_it@163.com
 */
public abstract class QBaseViewHolder<T> extends RecyclerView.ViewHolder {
    protected Context context;

    protected QBaseViewHolder(View itemView) {
        super(itemView);
        initializeView(itemView);
    }

    public QBaseViewHolder(View itemView, Context context) {
        super(itemView);
        this.context = context;
        initializeView(itemView);
    }

    public abstract void initializeView(View v);

    public void initializeData(T module) {
    }
}
