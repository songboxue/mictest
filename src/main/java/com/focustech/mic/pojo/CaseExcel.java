package com.focustech.mic.pojo;

import java.util.Date;

public class CaseExcel {
    private Integer id;

    private String filename;

    private Integer status;

    private String message;

    private Integer adderNo;

    private Date createTime;

    private byte[] attachment;

    public CaseExcel(Integer id, String filename, Integer status, String message, Integer adderNo, Date createTime, byte[] attachment) {
        this.id = id;
        this.filename = filename;
        this.status = status;
        this.message = message;
        this.adderNo = adderNo;
        this.createTime = createTime;
        this.attachment = attachment;
    }

    public CaseExcel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename == null ? null : filename.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message == null ? null : message.trim();
    }

    public Integer getAdderNo() {
        return adderNo;
    }

    public void setAdderNo(Integer adderNo) {
        this.adderNo = adderNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public byte[] getAttachment() {
        return attachment;
    }

    public void setAttachment(byte[] attachment) {
        this.attachment = attachment;
    }
}