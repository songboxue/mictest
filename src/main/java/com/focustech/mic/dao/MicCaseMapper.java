package com.focustech.mic.dao;

import com.focustech.mic.pojo.MicCase;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MicCaseMapper {
    int deleteByPrimaryKey(Integer caseId);

    int insert(MicCase record);

    int insertSelective(MicCase record);

    MicCase selectByPrimaryKey(Integer caseId);

    int updateByPrimaryKeySelective(MicCase record);

    int updateByPrimaryKey(MicCase record);

    List<MicCase> selectByPId(Integer pid);

    List<MicCase> selectCaseListByIds(List<Integer> caseIds);
}