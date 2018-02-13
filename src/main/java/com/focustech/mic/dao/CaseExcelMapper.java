package com.focustech.mic.dao;

import com.focustech.mic.pojo.CaseExcel;

public interface CaseExcelMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CaseExcel record);

    int insertSelective(CaseExcel record);

    CaseExcel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CaseExcel record);

    int updateByPrimaryKeyWithBLOBs(CaseExcel record);

    int updateByPrimaryKey(CaseExcel record);
}