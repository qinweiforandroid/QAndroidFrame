package com.qw.library.adapter;

import android.view.View;

/**
 * listview viewHolder 基类
 */
public abstract class QBaseViewHolder {
    public abstract void initializeView(View view);

    /**
     * @param position
     */
    public abstract void initializeData(int position);


}
