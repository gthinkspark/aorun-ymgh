package com.aorun.ymgh.dto;

public class WorkerAdvisoryDto {
    private Long id;

    private Integer advisoryType;

    private Integer advisoryBizType;

    private String advisoryName;

    private String telephone;

    private String companyName;

    private String advisoryTitle;

    private String advisoryContent;

    private String materialsUrls;

    private Integer status;

    private String failReason;

    private String createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
        this.advisoryName = advisoryName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getAdvisoryTitle() {
        return advisoryTitle;
    }

    public void setAdvisoryTitle(String advisoryTitle) {
        this.advisoryTitle = advisoryTitle;
    }

    public String getAdvisoryContent() {
        return advisoryContent;
    }

    public void setAdvisoryContent(String advisoryContent) {
        this.advisoryContent = advisoryContent;
    }

    public String getMaterialsUrls() {
        return materialsUrls;
    }

    public void setMaterialsUrls(String materialsUrls) {
        this.materialsUrls = materialsUrls;
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
        this.failReason = failReason;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}