package com.focustech.mic.common;

import com.alibaba.fastjson.annotation.JSONField;
import com.focustech.mic.constants.ResponseConst;

import java.io.Serializable;

/**
 * User: msi
 * Date: 2018/1/16 21:38
 * Description:
 */

public class ServerResponse<T> implements Serializable{

    private int code;

    private String msg;

    private T data;

    public ServerResponse() {
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public ServerResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ServerResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> ServerResponse<T> success() {
        return new ServerResponse<T>(ResponseConst.CODE, ResponseConst.SUCCESS);
    }

    public static <T> ServerResponse successData(T data) {
        return new ServerResponse<T>(ResponseConst.CODE, ResponseConst.SUCCESS, data);
    }

    public static <T> ServerResponse error(int code, String msg) {
        return new ServerResponse<T>(code, msg);
    }

    public static <T> ServerResponse errorData(int code, String msg, T data) {
        return new ServerResponse<T>(code, msg, data);
    }
}
