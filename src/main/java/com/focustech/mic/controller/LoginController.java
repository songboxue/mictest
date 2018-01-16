package com.focustech.mic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: songboxue
 * Date: 2018/1/16
 * Description:
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(value = "/cookie.do",method = RequestMethod.GET)
    public String loginByCookie(String cookie,Model model){
        model.addAttribute("user",cookie);
        return "index";
    }
}
