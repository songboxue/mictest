package com.focustech.mic.service.impl;

import com.focustech.mic.common.ServerResponse;
import com.focustech.mic.service.ILoginService;
import org.springframework.stereotype.Service;

/**
 * User: msi
 * Date: 2018/1/16 22:12
 * Description:
 */
@Service("iLoginService")
public class LoginServiceImpl implements ILoginService {
    @Override
    public ServerResponse setCookie(String cookie) {

    //接收客户端提交的cookie字符串，并以此创建httpclient对象
    //httpclient存储在当前线程中，做是否存在判断；
    //创建完成后，检查是否登录成功，返回对应的success or error



        return null;
    }

}
