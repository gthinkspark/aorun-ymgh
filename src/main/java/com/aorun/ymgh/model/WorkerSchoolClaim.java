package com.aorun.ymgh.model;

import java.util.Date;

public class WorkerSchoolClaim {
    private Long id;

    private Long workerId;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public Integer getIncome() {
        return income;
    }

    public void setIncome(Integer income) {
        this.income = income;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName == null ? null : companyName.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
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
        this.studentName = studentName == null ? null : studentName.trim();
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation == null ? null : relation.trim();
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
        this.enrollInfo = enrollInfo == null ? null : enrollInfo.trim();
    }

    public String getEnrollSchool() {
        return enrollSchool;
    }

    public void setEnrollSchool(String enrollSchool) {
        this.enrollSchool = enrollSchool == null ? null : enrollSchool.trim();
    }

    public String getSelfPayingCase() {
        return selfPayingCase;
    }

    public void setSelfPayingCase(String selfPayingCase) {
        this.selfPayingCase = selfPayingCase == null ? null : selfPayingCase.trim();
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