package com.evan.flp_time_control.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "tc_time_plan_detail")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class TimePlanDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "time_plan_detail_id")
    private int timePlanDetailId;

    private int timePlanId;
    private String startTime;
    private String endTime;
    private  String planContent;
    private String detailRemarks;
    private int addUser;
    private int modifyUser;
    private String addTime;
    private String modifyTime;
    private int isDelete;

    public String getTimeSlice() {
        return timeSlice;
    }

    public void setTimeSlice(String timeSlice) {
        this.timeSlice = timeSlice;
    }

    @Transient
    private String timeSlice;

    public int getTimePlanDetailId() {
        return timePlanDetailId;
    }

    public void setTimePlanDetailId(int timePlanDetailId) {
        this.timePlanDetailId = timePlanDetailId;
    }

    public int getTimePlanId() {
        return timePlanId;
    }

    public void setTimePlanId(int timePlanId) {
        this.timePlanId = timePlanId;
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

    public String getPlanContent() {
        return planContent;
    }

    public void setPlanContent(String planContent) {
        this.planContent = planContent;
    }

    public String getDetailRemarks() {
        return detailRemarks;
    }

    public void setDetailRemarks(String detailRemarks) {
        this.detailRemarks = detailRemarks;
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
