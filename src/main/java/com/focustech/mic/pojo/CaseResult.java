package com.focustech.mic.pojo;

import java.util.Date;

public class CaseResult {
    private Integer id;

    private Integer caseId;

    private Integer resultCode;

    private String resultMessage;

    private Date createTime;

    public CaseResult(Integer id, Integer caseId, Integer resultCode, String resultMessage, Date createTime) {
        this.id = id;
        this.caseId = caseId;
        this.resultCode = resultCode;
        this.resultMessage = resultMessage;
        this.createTime = createTime;
    }

    public CaseResult() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMessage() {
        return resultMessage;
    }

    public void setResultMessage(String resultMessage) {
        this.resultMessage = resultMessage == null ? null : resultMessage.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}