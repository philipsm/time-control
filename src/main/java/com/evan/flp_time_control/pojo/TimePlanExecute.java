package com.evan.flp_time_control.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "tc_time_plan_execute")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class TimePlanExecute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "execute_id")
    private int executeId;

    private int timePlanId;

    private String executeDate;
    private  int executeResult;

    private String title;
    private String remarks;

    private String addUser;
    private String modifyUser;
    private String addTime;
    private String modifyTime;
    private int isDelete;

    public int getExecuteId() {
        return executeId;
    }

    public void setExecuteId(int executeId) {
        this.executeId = executeId;
    }

    public int getTimePlanId() {
        return timePlanId;
    }

    public void setTimePlanId(int timePlanId) {
        this.timePlanId = timePlanId;
    }

    public String getExecuteDate() {
        return executeDate;
    }

    public void setExecuteDate(String executeDate) {
        this.executeDate = executeDate;
    }

    public int getExecuteResult() {
        return executeResult;
    }

    public void setExecuteResult(int executeResult) {
        this.executeResult = executeResult;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAddUser() {
        return addUser;
    }

    public void setAddUser(String addUser) {
        this.addUser = addUser;
    }

    public String getModifyUser() {
        return modifyUser;
    }

    public void setModifyUser(String modifyUser) {
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
