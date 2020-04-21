package com.aorun.ymgh.dto;

import java.util.List;
import java.util.Map;

public class WorkerSchoolClaimDto {
    private Long id;

    private String name;

    private Integer population;

    private Integer income;

    private String companyName;

    private String telephone;


    private Integer monthIncome;

    private Integer workerNumber;

    private String studentName;

    private String relation;

    private Integer examScore;

    private String enrollInfo;

    private String enrollSchool;

    private String selfPayingCase;


    private Integer status;

    private String failReason;

    private String createTime;

    private Integer isReaded;

    private String bankName;

    private String cardNumber;

    private String explainImgUrls;

    private List<Map<String,Object>> resMapList;

    private String resIds;

    public String getResIds() {
        return resIds;
    }

    public void setResIds(String resIds) {
        this.resIds = resIds;
    }
    public List<Map<String, Object>> getResMapList() {
        return resMapList;
    }

    public void setResMapList(List<Map<String, Object>> resMapList) {
        this.resMapList = resMapList;
    }

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

    public Integer getMonthIncome() {
        return monthIncome;
    }

    public void setMonthIncome(Integer monthIncome) {
        this.monthIncome = monthIncome;
    }

    public Integer getWorkerNumber() {
        return workerNumber;
    }

    public void setWorkerNumber(Integer workerNumber) {
        this.workerNumber = workerNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation;
    }

    public Integer getExamScore() {
        return examScore;
    }

    public void setExamScore(Integer examScore) {
        this.examScore = examScore;
    }

    public String getEnrollInfo() {
        return enrollInfo;
    }

    public void setEnrollInfo(String enrollInfo) {
        this.enrollInfo = enrollInfo;
    }

    public String getEnrollSchool() {
        return enrollSchool;
    }

    public void setEnrollSchool(String enrollSchool) {
        this.enrollSchool = enrollSchool;
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