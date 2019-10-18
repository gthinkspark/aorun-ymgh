package com.aorun.ymgh.dto;

public class WorkerMedicalClaimDto {
    private Long id;

    private String name;

    private Integer population;

    private Integer income;

    private String companyName;

    private String telephone;

    private String diseaseName;

    private String selfPayingCase;


    private Integer status;

    private String failReason;

    private String createTime;

    private Integer isReaded;


    private String bankName;

    private String cardNumber;

    private String explainImgUrls;


    public Integer getIsReaded() {
        return isReaded;
    }

    public void setIsReaded(Integer isReaded) {
        this.isReaded = isReaded;
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getPopulation() {
        return population;
    }

    public Integer getIncome() {
        return income;
    }

    public String getTelephone() {
        return telephone;
    }


    public Integer getStatus() {
        return status;
    }

    public String getFailReason() {
        return failReason;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setFailReason(String failReason) {
        this.failReason = failReason;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getSelfPayingCase() {
        return selfPayingCase;
    }

    public void setSelfPayingCase(String selfPayingCase) {
        this.selfPayingCase = selfPayingCase;
    }


    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExplainImgUrls() {
        return explainImgUrls;
    }

    public void setExplainImgUrls(String explainImgUrls) {
        this.explainImgUrls = explainImgUrls;
    }
}