package com.evan.flp_time_control.dao;

import com.evan.flp_time_control.pojo.TimePlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TimePlanDAO extends JpaRepository<TimePlan,Integer> {

    @Query(value = "select * from tc_time_plan where add_user = ?1 AND is_delete = 0 ",nativeQuery = true)
    List<TimePlan> findAllByAddUser(int userId);

    @Modifying(clearAutomatically=true)
    @Query(value = " update TimePlan t set t.isDelete = 1 where t.timePlanId = ?1")
    int deleteById(int timePlanId);

}
