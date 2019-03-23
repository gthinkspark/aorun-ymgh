package com.aorun.ymgh.model;

import java.util.Date;

public class WorkerLegalAid {
    private Long id;

    private Long workerId;

    private Long name;

    private Long companyName;

    private String telephone;

    private String caseReason;

    private String replyItems;

    private Integer status;

    private String failReason;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(Long workerId) {
        this.workerId = workerId;
    }

    public Long getName() {
        return name;
    }

    public void setName(Long name) {
        this.name = name;
    }

    public Long getCompanyName() {
        return companyName;
    }

    public void setCompanyName(Long companyName) {
        this.companyName = companyName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getCaseReason() {
        return caseReason;
    }

    public void setCaseReason(String caseReason) {
        this.caseReason = caseReason == null ? null : caseReason.trim();
    }

    public String getReplyItems() {
        return replyItems;
    }

    public void setReplyItems(String replyItems) {
        this.replyItems = replyItems == null ? null : replyItems.trim();
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFailReason() {
        return failReason;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason == null ? null : failReason.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}