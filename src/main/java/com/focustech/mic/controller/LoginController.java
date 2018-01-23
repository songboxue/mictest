package com.focustech.mic.controller;

import com.focustech.mic.common.ServerResponse;
import com.focustech.mic.service.ILoginService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * User: songboxue
 * Date: 2018/1/16
 * Description:
 */
@Controller
@RequestMapping("/loginVO")
public class LoginController {

    @Autowired
    private ILoginService iLoginService;

    //根据手动获取到的cookie登录到VO
    @RequestMapping(value = "/setCookie.do",method = RequestMethod.POST)
    public ServerResponse setCookie(String cookie){
        ServerResponse serverResponse = iLoginService.setCookie(cookie);

        return serverResponse;
    }

    //根据输入的帐密登录到VO
    @RequestMapping(value = "/userLogin.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse loginVOByUsername(String username,String password) throws IOException {
        ServerResponse serverResponse =  iLoginService.loginVOByUsername(username,password);
        return serverResponse;
    }

    //获取首页
    @RequestMapping(value = "/getIndex.do")
    @ResponseBody
    public ServerResponse getVoIndex() {
        String voIndexPage = iLoginService.getVoIndexPage();
        return ServerResponse.successData(voIndexPage);
    }
}
