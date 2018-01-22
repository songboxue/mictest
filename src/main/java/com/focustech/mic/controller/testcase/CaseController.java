package com.focustech.mic.controller.testcase;

import com.focustech.mic.common.ServerResponse;
import com.focustech.mic.pojo.MicCase;
import com.focustech.mic.service.ICaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

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

    //增加用例
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCase(MicCase micCase){
        if(micCase == null){
            return ServerResponse.error(1,"数据为空");
        }
        ServerResponse response = iCaseService.addCase(micCase);
        return response;
    }

    //根据项目id获取用例数据
    @RequestMapping(value = "/getList")
    public String getCaseList(@RequestParam(value = "pid",required = false)Integer pid, Model model){
        List<MicCase> micCaseList = new ArrayList<>();
        micCaseList = iCaseService.getCaseList(pid);
        model.addAttribute("mcList",micCaseList);
        return "case/list";
    }

    //更新用例
    @RequestMapping(value = "update")
    public String updateCase(MicCase micCase){
        //todo
        return null;
    }
}
