package com.qw.frame.model.impl;

import com.qw.frame.entity.Meizhi;
import com.qw.frame.model.BaseModel;
import com.qw.frame.model.IGankModel;
import com.qw.frame.utils.GankIoCallback;
import com.qw.library.net.AppException;
import com.qw.library.net.Request;
import com.qw.library.net.RequestManager;

import java.util.ArrayList;

/**
 * Created by qinwei on 16/5/18 下午2:52
 */
public class GankModel extends BaseModel implements IGankModel{
    public ArrayList<Meizhi> meizhis;

    @Override
    public void loadGankByPage(int pageNum) {
        Request request = new Request("http://gank.io/api/data/福利/20/" + pageNum);
        request.setCallback(new GankIoCallback<ArrayList<Meizhi>>() {
            @Override
            public void onSuccess(ArrayList<Meizhi> result) {
                meizhis=result;
                onResponseSuccess(IGankModel.ACTION_LOAD_GANK_PICTURE_LIST);
            }

            @Override
            public void onFailure(AppException e) {
                e.printStackTrace();
                onResponseError(IGankModel.ACTION_LOAD_GANK_PICTURE_LIST,e.responseCode,e.responseMessage);
            }
        });
        RequestManager.getInstance().execute(this.toString(), request);
    }
}
