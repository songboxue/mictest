package com.focustech.mic.service;

import com.focustech.mic.common.ServerResponse;

import java.io.IOException;

/**
 * User: msi
 * Date: 2018/1/16 22:11
 * Description:
 */
public interface ILoginService {
    ServerResponse setCookie(String cookie);

    ServerResponse loginVOByUsername(String username, String password) throws IOException;

    String getVoIndexPage(String username);
}
