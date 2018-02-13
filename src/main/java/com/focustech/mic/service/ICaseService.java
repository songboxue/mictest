package com.focustech.mic.service;

import com.focustech.mic.common.ServerResponse;
import com.focustech.mic.pojo.MicCase;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * User: songboxue
 * Date: 2018/1/18
 * Description:
 */
public interface ICaseService {
    ServerResponse addCase(MicCase micCase);

    List<MicCase> getCaseList(Integer pid);

    ServerResponse updateCase(MicCase micCase);

    ServerResponse exeCase(Integer caseId);

    ServerResponse getDetail(String caseId);

    ServerResponse dealCaseExcel(String name, MultipartFile file);

    ServerResponse batchExecute(List<Integer> caseIds);
}
