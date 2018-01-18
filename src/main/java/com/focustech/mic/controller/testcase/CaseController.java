package com.focustech.mic.controller.testcase;

import com.focustech.mic.common.ServerResponse;
import com.focustech.mic.pojo.MicCase;
import com.focustech.mic.service.ICaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * User: songboxue
 * Date: 2018/1/18
 * Description:
 */
@Controller
@RequestMapping("/case")
public class CaseController {

    @Autowired
    private ICaseService iCaseService;

    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCase(MicCase micCase){
        if(micCase == null){
            return ServerResponse.error(1,"数据为空");
        }
        ServerResponse response = iCaseService.addCase(micCase);
        return response;
    }
}
