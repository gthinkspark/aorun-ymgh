package com.aorun.ymgh.model;

import java.util.Date;

public class WorkerLegalAid {
    private Long id;

    private Long workerId;

    private String name;

    private String companyName;

    private String telephone;

    private String caseReason;

    private String replyItems;

    private Integer status;

    private String failReason;

    private Date createTime;

    private Date updateTime;

    private String explainImgUrls;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
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

    public String getExplainImgUrls() {
        return explainImgUrls;
    }

    public void setExplainImgUrls(String explainImgUrls) {
        this.explainImgUrls = explainImgUrls;
    }
}