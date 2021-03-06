package com.focustech.mic.controller.testcase;

import com.focustech.mic.common.ServerResponse;
import com.focustech.mic.pojo.MicCase;
import com.focustech.mic.service.ICaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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

    private Logger logger = LoggerFactory.getLogger(CaseController.class);

    @Autowired
    private ICaseService iCaseService;

    //增加用例
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addCase(@RequestBody MicCase micCase){
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
        return "/case/index";
    }

    //根据caseId获取用例的详细信息
    @RequestMapping(value = "/detail" ,method = RequestMethod.POST)
    @ResponseBody
    //todo 使用valid进行参数校验，需要自定义错误处理类
    public ServerResponse getDetail(@RequestParam(value = "caseId") String caseId){
        if(caseId == null || caseId.equals("")){
            return ServerResponse.error(1,"参数为空");
        }
        return iCaseService.getDetail(caseId);
    }

    //更新用例
    @RequestMapping(value = "/update",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateCase(@RequestBody MicCase micCase){
        if(micCase == null){
            return ServerResponse.error(1,"更新数据为空");
        }
        return iCaseService.updateCase(micCase);
    }

    //执行单个用例
    @RequestMapping(value = "/execute")
    @ResponseBody
    public ServerResponse executeCase(Integer caseId){
        if(caseId == null){
            return ServerResponse.error(1,"请选择要执行的用例");
        }
        return iCaseService.exeCase(caseId);
    }

    @RequestMapping(value = "/upload")
    @ResponseBody
    public String uploadCaseExcel(MultipartFile file,Model model){
        if(file == null || file.getSize()==0){
            return null;
        }
        logger.info("接收文件："+file.getOriginalFilename());

        String name = file.getOriginalFilename();
        ServerResponse respData = iCaseService.dealCaseExcel(name,file);

        model.addAttribute("code",respData.getCode());
        model.addAttribute("msg",respData.getMsg());
        if(respData.getCode() != 0){
            model.addAttribute("data",respData.getData());
        }
        //处理完毕后返回更新后的用例页面，加入错误信息模块
        return "/case/result";
    }

    //批量执行
    @RequestMapping(value = "/batchExecute")
    @ResponseBody
    public ServerResponse batchExecute(List<Integer> caseIds){
        if(caseIds == null){
            return ServerResponse.error(1,"请选择要执行的用例");
        }
        return iCaseService.batchExecute(caseIds);
    }
}
