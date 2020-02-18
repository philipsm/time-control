package com.evan.flp_time_control.dao;

import com.evan.flp_time_control.pojo.TimePlanDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimePlanDetailDAO extends JpaRepository<TimePlanDetail,Integer> {



    /**
     * 根据ID删除计划详情
     */
    @Modifying(clearAutomatically=true)
    @Query(value = " update TimePlanDetail t set t.isDelete = 1 where t.timePlanDetailId = ?1 ")
    int deleteByTimePlanDetailId(int timePlanDetailId);


    /**
     * 根据添加用户查询对应的所有计划详情
     * @param addUserId
     * @return
     */
    @Query(value = "select * from tc_time_plan_detail where add_user = ?1 and is_delete = 0",nativeQuery = true)
    List<TimePlanDetail> getTimePlanDetailListByUser(int addUserId);
}
