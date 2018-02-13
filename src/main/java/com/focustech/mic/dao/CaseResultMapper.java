package com.focustech.mic.dao;

import com.focustech.mic.pojo.CaseResult;

public interface CaseResultMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CaseResult record);

    int insertSelective(CaseResult record);

    CaseResult selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CaseResult record);

    int updateByPrimaryKey(CaseResult record);
}