package com.evan.flp_time_control.service;

import com.evan.flp_time_control.dao.TimePlanDAO;
import com.evan.flp_time_control.pojo.TimePlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimePlanService {

    @Autowired
    TimePlanDAO timePlanDAO;

    /**
     * 根据用户获取计划列表
     * @param userId
     * @return
     */
    public List<TimePlan> getTimePlanListByUser(int userId){
        List<TimePlan> timePlanList = timePlanDAO.findAllByAddUser(userId);
        return timePlanList;
    }

   /**
    * 保存或修改
    */
   public TimePlan addOrUpdate(TimePlan timePlan){
       TimePlan result = timePlanDAO.save(timePlan);
       return result;
   }

    /**
     * 假删除
     * @param id
     */
    public int deleteById(int id) {
        int result = timePlanDAO.deleteById(id);
        return  result;
    }



}
