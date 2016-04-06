package com.qw.library.widget;

/**
 * Created by qinwei on 2016/4/6 13:52
 * email:qinwei_it@163.com
 */
public interface IFooterView {
    enum State {
        /**
         * 正在加载
         */
        ing,
        /**
         * 加载成功
         */
        done,
        /**
         * 加载失败
         */
        error,
        /**
         * 无数据
         */
        no_data
    }

    void notifyDataChanged(State status);
}