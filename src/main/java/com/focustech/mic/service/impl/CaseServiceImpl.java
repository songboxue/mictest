package com.focustech.mic.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.focustech.mic.common.ServerResponse;
import com.focustech.mic.constants.CaseConst;
import com.focustech.mic.dao.MicCaseMapper;
import com.focustech.mic.pojo.MicCase;
import com.focustech.mic.service.ICaseService;
import com.focustech.mic.util.ExcelUtil;
import com.focustech.mic.util.HttpUtil;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
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
        if (result < 1) {
            return ServerResponse.error(1, "插入失败");
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
        if (result < 1) {
            return ServerResponse.error(2, "更新数据库失败");
        }
        return ServerResponse.success();
    }

    @Override
    public ServerResponse getDetail(String caseId) {
        MicCase micCase = micCaseMapper.selectByPrimaryKey(Integer.valueOf(caseId));
        if (micCase == null) {
            return ServerResponse.error(2, "找不到此记录");
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
        int colSize = ExcelUtil.getIndexByLetter("I") - startColumn + 1;
        List<List<String>> dataList = null;

        //todo 校验文件，错误就抛出异常
        validate(name);
        //转换成字节数组
        byte[] buffer;
        try {
            buffer = file.getBytes();
            dataList = ExcelUtil.parseExcel(startRow, startColumn, colSize, buffer);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }

        int dataSize = dataList.size();
        logger.info("共获取到" + dataSize + "条记录");

        //开始转换并插库
        int successNum = 0;
        List<List<String>> errorList = new ArrayList<>();//错误列表
        for (int i = 0; i < dataSize; i++) {
            try {
                MicCase micCase = new MicCase();
                boolean succFlag = convert(micCase, dataList.get(i));
                //插入数据库
                if (!succFlag) {
                    errorList.add(dataList.get(i));
                    continue;
                }
                int insertNum = micCaseMapper.insertSelective(micCase);
                successNum += insertNum;
            } catch (Exception e) {
                continue;
            }

        }
        logger.info("成功插入数据库" + successNum + "条");

        if (successNum == dataSize) {
            return ServerResponse.success();
        } else if (successNum == 0 && dataSize > 0) {
            return ServerResponse.errorData(3, "导入失败", errorList);
        }
        return ServerResponse.errorData(2, "部分数据处理错误", errorList);
    }

    private void validate(String name) {
    }


    /*
    将excel中获取到的数据转换成JavaBean形式
     */
    private boolean convert(MicCase micCase, List<String> dataList) {
        try {
            ExcelUtil.listToBean(micCase, dataList, CaseConst.CASE_FEILDS);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
    执行对应的用例
     */
    @Override
    public ServerResponse exeCase(Integer caseId) {
        //取出对应的case
        MicCase micCase = micCaseMapper.selectByPrimaryKey(caseId);
        if (micCase == null) {
            return ServerResponse.error(1, "数据库中无此记录");
        }
        //解析header，将传入的json字符串转换成Header对象        ;
        List<Header> headerList = resolveHeader(micCase.getDataHeader());
        String url = micCase.getDataUrl();
        //根据contentType处理请求
        String headerStr = micCase.getDataHeader();

        //如果自定义了消息格式，就要重新封装body
        String body = micCase.getDataSend();
        if (headerStr != null && !"".equals(headerStr)
                && headerStr.indexOf("Content-Type") != -1) {
            //如果指定了Content-Type的内容，就需要自定义消息格式
            String contentType = getValueByKey(headerStr, "Content-Type");
            body = resolveBody(micCase.getDataSend(), contentType);
        }

        //调用请求
        String result = HttpUtil.doPost(url, headerList,body);
        return ServerResponse.successData(result);
    }

    /*
    从一个JSON字符串中根据key获取到对应的value
     */
    private String getValueByKey(String str, String key) {
        JSONObject jo = JSON.parseObject(str);
        String value = jo.getString(key);
        return value == null ? "" : value;

    }

    /*
    真正解析header字符串的方法
     */
    private List<Header> resolveHeader(String headerStr) {
        if (headerStr == null && "".equals(headerStr)) {
            return null;
        }
        Header header;
        List<Header> headers = new ArrayList<>();
        JSONObject jo = JSON.parseObject(headerStr);
        for (Map.Entry<String, Object> entry : jo.entrySet()) {
            header = new BasicHeader(entry.getKey(), entry.getValue().toString());
            headers.add(header);
        }
        return headers;
    }

    /*
    根据不同的content-type将存储的json格式的消息体转换成需要的字符串
     */
    private String resolveBody(String body, String contentType) {
        String bodyStr;
        switch(contentType){
            case CaseConst.CONTENTTYPE_JSON:
               //默认就是application/json
                bodyStr = body;
                break;
            case CaseConst.CONTENTTYPE_URLENCODED:
                //将json装换成普通表单形式
                bodyStr = JSONToUrlencoded(body);
                break;
            //其他的消息格式日后再写，如form-data
            default:
                bodyStr = body;
                break;
        }
        return bodyStr;
    }

    /*
    将json字符串装换成x-www-form-urlencoded形式的字符串
    因为我现在收集到的表单形式字符串都是key=value的格式，所以不做递归遍历了，能解析成啥样就啥样
     */
    private String JSONToUrlencoded(String jsonStr) {
        JSONObject jo = JSON.parseObject(jsonStr);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : jo.entrySet()) {
            sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
        }
        return sb.deleteCharAt(sb.length()-1).toString();
    }
}
