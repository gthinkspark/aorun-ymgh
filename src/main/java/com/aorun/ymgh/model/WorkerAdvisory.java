package com.aorun.ymgh.model;

import java.util.Date;

public class WorkerAdvisory {
    private Long id;

    private Long workerId;

    private Integer advisoryType;

    private Integer advisoryBizType;

    private String advisoryName;

    private String telephone;

    private String companyName;

    private String advisoryTitle;

    private String advisoryContent;

    private Long attorneyId;

    private String materialsUrls;

    private Integer status;

    private String failReason;

    private String isDeleted;

    private Integer isReaded;

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

    public Integer getAdvisoryType() {
        return advisoryType;
    }

    public void setAdvisoryType(Integer advisoryType) {
        this.advisoryType = advisoryType;
    }

    public Integer getAdvisoryBizType() {
        return advisoryBizType;
    }

    public void setAdvisoryBizType(Integer advisoryBizType) {
        this.advisoryBizType = advisoryBizType;
    }

    public String getAdvisoryName() {
        return advisoryName;
    }

    public void setAdvisoryName(String advisoryName) {
        this.advisoryName = advisoryName == null ? null : advisoryName.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getAdvisoryTitle() {
        return advisoryTitle;
    }

    public void setAdvisoryTitle(String advisoryTitle) {
        this.advisoryTitle = advisoryTitle == null ? null : advisoryTitle.trim();
    }

    public String getAdvisoryContent() {
        return advisoryContent;
    }

    public void setAdvisoryContent(String advisoryContent) {
        this.advisoryContent = advisoryContent == null ? null : advisoryContent.trim();
    }

    public Long getAttorneyId() {
        return attorneyId;
    }

    public void setAttorneyId(Long attorneyId) {
        this.attorneyId = attorneyId;
    }

    public String getMaterialsUrls() {
        return materialsUrls;
    }

    public void setMaterialsUrls(String materialsUrls) {
        this.materialsUrls = materialsUrls == null ? null : materialsUrls.trim();
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

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
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

    public Integer getIsReaded() {
        return isReaded;
    }

    public void setIsReaded(Integer isReaded) {
        this.isReaded = isReaded;
    }
}