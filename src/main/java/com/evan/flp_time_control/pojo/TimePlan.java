package com.evan.flp_time_control.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

//@DynamicInsert
@Entity
@Table(name = "tc_time_plan")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class TimePlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_plan_id")
    private int timePlanId;

    private String title;
    private String planDescribe;
    private String date;

    private  int planType;
    private int planGroup;
    private int addUser;
    private int modifyUser;
    private String addTime;
    private String modifyTime;
    private int isDelete;

    public int getTimePlanId() {
        return timePlanId;
    }

    public void setTimePlanId(int timePlanId) {
        this.timePlanId = timePlanId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPlanDescribe() {
        return planDescribe;
    }

    public void setPlanDescribe(String planDescribe) {
        this.planDescribe = planDescribe;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPlanType() {
        return planType;
    }

    public void setPlanType(int planType) {
        this.planType = planType;
    }

    public int getPlanGroup() {
        return planGroup;
    }

    public void setPlanGroup(int planGroup) {
        this.planGroup = planGroup;
    }

    public int getAddUser() {
        return addUser;
    }

    public void setAddUser(int addUser) {
        this.addUser = addUser;
    }

    public int getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(int modifyUser) {
        this.modifyUser = modifyUser;
    }

    public String getAddTime() {
        return addTime;
    }

    public void setAddTime(String addTime) {
        this.addTime = addTime;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
    }

    public int getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(int isDelete) {
        this.isDelete = isDelete;
    }
}
