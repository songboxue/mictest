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

import java.io.IOException;

/**
 * User: songboxue
 * Date: 2018/1/16
 * Description:
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private ILoginService iLoginService;

    @RequestMapping(value = "/cookie.do",method = RequestMethod.GET)
    public String loginByCookie(){
        String url = "http://membercenter.cn.made-in-china.com/member/main/";
        String result = null;
        HttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Cookie","");
        try{
            HttpResponse resp = client.execute(httpGet);
            HttpEntity httpEntity = resp.getEntity();
            result = EntityUtils.toString(httpEntity);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(result);
        return null;
    }

    @RequestMapping(value = "/setCookie.do",method = RequestMethod.POST)
    public ServerResponse setCookie(String cookie){
        ServerResponse serverResponse = iLoginService.setCookie(cookie);


        return serverResponse;
    }
}
