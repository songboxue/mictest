package com.focustech.mic.service.impl;

import com.focustech.mic.common.ServerResponse;
import com.focustech.mic.constants.CaseConst;
import com.focustech.mic.dao.MicCaseMapper;
import com.focustech.mic.pojo.MicCase;
import com.focustech.mic.service.ICaseService;
import com.focustech.mic.util.ExcelUtil;
import com.focustech.mic.util.HttpUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * User: songboxue
 * Date: 2018/1/18
 * Description:
 */
@Service("iCaseService")
public class CaseServiceImpl implements ICaseService {

    private Logger logger = LoggerFactory.getLogger(CaseServiceImpl.class);

    @Autowired
    private MicCaseMapper micCaseMapper;

    @Override
    public ServerResponse addCase(MicCase micCase) {
        int result = micCaseMapper.insert(micCase);
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

    /*
    处理用例Excel文件的service实现方法
     */
    @Override
    public ServerResponse dealCaseExcel(String name, MultipartFile file) {
        //excel的内容起始行及起止列，注意起始下标是0
        int startRow = 1;
        int startColumn = ExcelUtil.getIndexByLetter("A");
        int colSize = ExcelUtil.getIndexByLetter("I")-startColumn +1;
        List<List<String>> dataList = null;

        //todo 校验文件，错误就抛出异常
        validate(name);
        //转换成字节数组
        byte[] buffer;
        try{
            buffer = file.getBytes();
            dataList = ExcelUtil.parseExcel(startRow,startColumn,colSize,buffer);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        int dataSize = dataList.size();
        logger.info("共获取到"+dataSize+"条记录");

        //开始转换并插库
        int successNum = 0;
        List<List<String>> errorList = new ArrayList<>();//错误列表
        for(int i=0;i<dataSize;i++){
            try{
                MicCase micCase = new MicCase();
                boolean succFlag = convert(micCase,dataList.get(i));
                //插入数据库
                if(!succFlag){
                    errorList.add(dataList.get(i));
                    continue;
                }
                int insertNum = micCaseMapper.insertSelective(micCase);
                successNum += insertNum;
            }catch (Exception e){
                continue;
            }

        }
        logger.info("成功插入数据库"+successNum+"条");

        if(successNum == dataSize){
            return ServerResponse.success();
        }else if(successNum == 0 && dataSize>0){
            return ServerResponse.errorData(3,"导入失败",errorList);
        }
        return ServerResponse.errorData(2,"部分数据处理错误",errorList);
    }

    private void validate(String name) {
    }


    /*
    将excel中获取到的数据转换成JavaBean形式
     */
    private boolean convert(MicCase micCase, List<String> dataList) {
        try{
            ExcelUtil.listToBean(micCase,dataList, CaseConst.caseFeilds);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }
}
