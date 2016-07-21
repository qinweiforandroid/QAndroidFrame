package com.qw.frame.model.impl;

import com.qw.frame.entity.Meizhi;
import com.qw.frame.model.BaseModel;
import com.qw.frame.model.IGankModel;
import com.qw.frame.utils.GankIoCallback;
import com.qw.library.http.AppException;
import com.qw.library.http.QHttpResponse;
import com.qw.library.http.Request;
import com.qw.library.http.RequestManager;

import java.util.ArrayList;

/**
 * Created by qinwei on 16/5/18 下午2:52
 */
public class GankModel extends BaseModel implements IGankModel {
    public ArrayList<Meizhi> meizhis;

    @Override
    public void loadGankByPage(int pageNum) {

        Request request = new Request("http://gank.io/api/data/福利/20/" + pageNum);
        request.setCallback(new GankIoCallback<ArrayList<Meizhi>>() {
            @Override
            public void onSuccess(QHttpResponse<ArrayList<Meizhi>> qHttpResponse) {
                meizhis = qHttpResponse.body;
                onResponseSuccess(IGankModel.ACTION_LOAD_GANK_PICTURE_LIST);
            }

            @Override
            public void onFailure(AppException e) {
                e.printStackTrace();
                onResponseError(IGankModel.ACTION_LOAD_GANK_PICTURE_LIST, e.responseCode, e.responseMessage);
            }
        });
        RequestManager.getInstance().enqueue(this.toString(), request);
    }
}
