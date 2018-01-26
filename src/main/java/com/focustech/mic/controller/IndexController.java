package com.focustech.mic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: songboxue
 * Date: 2018/1/26
 * Description:
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"/","/index"},method = RequestMethod.GET)
    public String getIndexPage(){
        return "index";
    }
}
