package com.evan.flp_time_control.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "tc_time_plan_execute_detail")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class TimePlanExecuteDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "execute_detail_id")
    private int executeDetailId;

    private int executeId;

    private int timePlanDetailId;

    private int executeResult;

    private String startTime;
    private String endTime;
    private  int planContent;
    private int detailRemarks;
    private String addUser;
    private String modifyUser;
    private String addTime;
    private String modifyTime;
    private int isDelete;

    public int getExecuteDetailId() {
        return executeDetailId;
    }

    public void setExecuteDetailId(int executeDetailId) {
        this.executeDetailId = executeDetailId;
    }

    public int getExecuteId() {
        return executeId;
    }

    public void setExecuteId(int executeId) {
        this.executeId = executeId;
    }

    public int getTimePlanDetailId() {
        return timePlanDetailId;
    }

    public void setTimePlanDetailId(int timePlanDetailId) {
        this.timePlanDetailId = timePlanDetailId;
    }

    public int getExecuteResult() {
        return executeResult;
    }

    public void setExecuteResult(int executeResult) {
        this.executeResult = executeResult;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getPlanContent() {
        return planContent;
    }

    public void setPlanContent(int planContent) {
        this.planContent = planContent;
    }

    public int getDetailRemarks() {
        return detailRemarks;
    }

    public void setDetailRemarks(int detailRemarks) {
        this.detailRemarks = detailRemarks;
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
