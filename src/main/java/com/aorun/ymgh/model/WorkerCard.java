package com.aorun.ymgh.model;

import java.util.Date;

public class WorkerCard {
    private Long id;

    private String name;

    private String bannerUrl;

    private String simpleContent;

    private String functionUrl;

    private String applyConditionUrl;

    private String serviceConditionUrl;

    private Date beginTime;

    private Date endTime;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getBannerUrl() {
        return bannerUrl;
    }

    public void setBannerUrl(String bannerUrl) {
        this.bannerUrl = bannerUrl == null ? null : bannerUrl.trim();
    }

    public String getSimpleContent() {
        return simpleContent;
    }

    public void setSimpleContent(String simpleContent) {
        this.simpleContent = simpleContent == null ? null : simpleContent.trim();
    }

    public String getFunctionUrl() {
        return functionUrl;
    }

    public void setFunctionUrl(String functionUrl) {
        this.functionUrl = functionUrl == null ? null : functionUrl.trim();
    }

    public String getApplyConditionUrl() {
        return applyConditionUrl;
    }

    public void setApplyConditionUrl(String applyConditionUrl) {
        this.applyConditionUrl = applyConditionUrl == null ? null : applyConditionUrl.trim();
    }

    public String getServiceConditionUrl() {
        return serviceConditionUrl;
    }

    public void setServiceConditionUrl(String serviceConditionUrl) {
        this.serviceConditionUrl = serviceConditionUrl == null ? null : serviceConditionUrl.trim();
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
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