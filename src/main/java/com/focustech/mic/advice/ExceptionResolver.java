package com.focustech.mic.advice;

import com.focustech.mic.exception.JSONParseException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * User: songboxue
 * Date: 2018/2/7
 * Description:
 */
public class ExceptionResolver implements HandlerExceptionResolver{

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse
            httpServletResponse, Object o, Exception e) {
        e.printStackTrace();
        JSONParseException jsonParseException = null;

        //如果抛出的是系统自定义的异常则直接转换
        if(e instanceof JSONParseException) {
            jsonParseException = (JSONParseException) e;
        } else {
            //如果抛出的不是系统自定义的异常则重新构造一个未知错误异常
            //这里我就也有JSONParseException省事了，实际中应该要再定义一个新的异常
            jsonParseException = new JSONParseException("系统未知错误");
        }

        //向前台返回错误信息
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("message", jsonParseException.getMessage());
        modelAndView.setViewName("error");

        return modelAndView;
    }
}
