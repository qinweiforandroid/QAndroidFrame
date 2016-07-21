package com.qw.frame.utils;


import com.qw.library.http.AbstractCallback;
import com.qw.library.http.AppException;
import com.qw.library.http.JsonParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by qinwei on 2016/4/21 14:34
 * email:qinwei_it@163.com
 */
public abstract class GankIoCallback<T> extends AbstractCallback<T> {
    public T convert(byte[] bfs) throws AppException {

        try {
            Type type = this.getClass().getGenericSuperclass();
            type = ((ParameterizedType) type).getActualTypeArguments()[0];
            String content = new String(bfs, "utf-8");
            JSONObject obj = new JSONObject(content);
            boolean result = obj.getBoolean("error");
            if (result) {
                throw new AppException(AppException.ErrorType.MANUAL, "manual");
            } else {
                return JsonParser.deserializeFromJson(obj.opt("results").toString(), type);
            }
        } catch (JSONException e) {
            throw new AppException(AppException.ErrorType.PARSE_JSON, e.getMessage());
        } catch (UnsupportedEncodingException e) {
            throw new AppException(AppException.ErrorType.CONVERT, e.getMessage());
        }
    }


}
