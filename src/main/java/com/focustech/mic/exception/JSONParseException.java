package com.focustech.mic.exception;

/**
 * User: songboxue
 * Date: 2018/2/7
 * Description:
 */
//todo 在解析json时统一抛出此异常
public class JSONParseException extends Exception{


    //异常信息
    public String message;

    public JSONParseException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
