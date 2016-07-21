package com.qw.frame.model;


import com.qw.library.http.RequestManager;

/**
 * Created by qinwei on 2015/12/18 16:10
 * email:qinwei_it@163.com
 */
public abstract class BaseModel {
    protected Controller controller;
    private OnProgressUpdatedListener listener;


    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void setOnProgressUpdatedListener(OnProgressUpdatedListener listener) {
        this.listener = listener;
    }

    protected void onProgressUpdated(String tag, long curPos, long contentLength) {
        if (listener != null) {
            listener.onProgressUpdated(tag, curPos, contentLength);
        }
    }

    protected void onResponseSuccess(String tag) {
        if (controller != null) {
            controller.onSuccess(tag);
        }
    }

    protected void onResponseError(String tag, int errorCode, String errorMsg) {
        if (controller != null) {
            controller.onFailure(tag, errorCode, errorMsg);
        }
    }


//    @Override
//    public boolean handleException(AppException e) {
//        switch (e.type) {
//            case IO:
//            case PARSE_JSON:
//            case SERVER:
//                if (e.responseCode == 403) {
////                    FIXME go login
//                } else {
//                    showToast("网络不给力啊");
//                }
//                break;
//            case TIMEOUT:
//                showToast("连接超时，请重试");
//                break;
//        }
//        return true;
//    }
}
