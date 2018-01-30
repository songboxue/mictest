package com.focustech.mic.service.impl;

import com.focustech.mic.common.ServerResponse;
import com.focustech.mic.dao.MicCaseMapper;
import com.focustech.mic.pojo.MicCase;
import com.focustech.mic.service.ICaseService;
import com.focustech.mic.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: songboxue
 * Date: 2018/1/18
 * Description:
 */
@Service("iCaseService")
public class CaseServiceImpl implements ICaseService {

    @Autowired
    private MicCaseMapper micCaseMapper;

    @Override
    public ServerResponse addCase(MicCase micCase) {
        int result = micCaseMapper.insertSelective(micCase);
        if(result < 1){
            return ServerResponse.error(1,"插入失败");
        }
        return ServerResponse.success();
    }

    @Override
    public List<MicCase> getCaseList(Integer pid) {
        List<MicCase> micCases = micCaseMapper.selectByPId(pid);
        return micCases;
    }

    @Override
    public ServerResponse updateCase(MicCase micCase) {
        int result = micCaseMapper.updateByPrimaryKeySelective(micCase);
        if(result<1){
            return ServerResponse.error(2,"更新数据库失败");
        }
        return ServerResponse.success();
    }

    @Override
    public ServerResponse exeCase(Integer caseId) {
        String url,body,result;
        //取出对应的case
        MicCase micCase = micCaseMapper.selectByPrimaryKey(caseId);
        if(micCase == null){
            return ServerResponse.error(1,"数据库中无此记录");
        }
        url = micCase.getDataUrl();
        body = micCase.getDataSend();
        result = HttpUtil.doPost(url,body);
        return ServerResponse.successData(result);
    }

    @Override
    public ServerResponse getDetail(String caseId) {
        MicCase micCase = micCaseMapper.selectByPrimaryKey(Integer.valueOf(caseId));
        if(micCase == null){
            return ServerResponse.error(2,"找不到此记录");
        }
        return ServerResponse.successData(micCase);
    }
}
