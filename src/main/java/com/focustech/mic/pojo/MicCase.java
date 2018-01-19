package com.focustech.mic.pojo;

import java.util.Date;

public class MicCase {
    private Integer caseId;

    private String caseName;

    private String caseDesc;

    private Integer projectId;

    private String dataUrl;

    private String dataSend;

    private String dataExpect;

    private Integer caseStatus;

    private Integer adderNo;

    private String adderName;

    private Date createTime;

    private Integer updateNo;

    private String updateName;

    private Date updateTime;

    public MicCase(Integer caseId, String caseName, String caseDesc, Integer projectId, String dataUrl, String dataSend, String dataExpect, Integer caseStatus, Integer adderNo, String adderName, Date createTime, Integer updateNo, String updateName, Date updateTime) {
        this.caseId = caseId;
        this.caseName = caseName;
        this.caseDesc = caseDesc;
        this.projectId = projectId;
        this.dataUrl = dataUrl;
        this.dataSend = dataSend;
        this.dataExpect = dataExpect;
        this.caseStatus = caseStatus;
        this.adderNo = adderNo;
        this.adderName = adderName;
        this.createTime = createTime;
        this.updateNo = updateNo;
        this.updateName = updateName;
        this.updateTime = updateTime;
    }

    public MicCase() {
        super();
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName == null ? null : caseName.trim();
    }

    public String getCaseDesc() {
        return caseDesc;
    }

    public void setCaseDesc(String caseDesc) {
        this.caseDesc = caseDesc == null ? null : caseDesc.trim();
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl == null ? null : dataUrl.trim();
    }

    public String getDataSend() {
        return dataSend;
    }

    public void setDataSend(String dataSend) {
        this.dataSend = dataSend == null ? null : dataSend.trim();
    }

    public String getDataExpect() {
        return dataExpect;
    }

    public void setDataExpect(String dataExpect) {
        this.dataExpect = dataExpect == null ? null : dataExpect.trim();
    }

    public Integer getCaseStatus() {
        return caseStatus;
    }

    public void setCaseStatus(Integer caseStatus) {
        this.caseStatus = caseStatus;
    }

    public Integer getAdderNo() {
        return adderNo;
    }

    public void setAdderNo(Integer adderNo) {
        this.adderNo = adderNo;
    }

    public String getAdderName() {
        return adderName;
    }

    public void setAdderName(String adderName) {
        this.adderName = adderName == null ? null : adderName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUpdateNo() {
        return updateNo;
    }

    public void setUpdateNo(Integer updateNo) {
        this.updateNo = updateNo;
    }

    public String getUpdateName() {
        return updateName;
    }

    public void setUpdateName(String updateName) {
        this.updateName = updateName == null ? null : updateName.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}