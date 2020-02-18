package com.evan.flp_time_control.service;

import com.evan.flp_time_control.dao.TimePlanDetailDAO;
import com.evan.flp_time_control.pojo.TimePlanDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

//@Transactional
@Service
public class TimePlanDetailService {

    @Autowired
    TimePlanDetailDAO timePlanDetailDAO;

   /**
    * 保存或修改
    */
   public TimePlanDetail addOrUpdate(TimePlanDetail timePlanDetail){
       TimePlanDetail result = timePlanDetailDAO.save(timePlanDetail);
       return result;
   }

    /**
     * 假删除
     * @param id
     */
    @Transactional
    public int deleteByTimePlanDetailId(int id) {
        int result = timePlanDetailDAO.deleteByTimePlanDetailId(id);
        return  result;
    }

    /**
     * 根据添加用户查询列表数据
     */
    public List<TimePlanDetail> getTimePlanDetailListByUser(int addUserId){
        List<TimePlanDetail> dataList = timePlanDetailDAO.getTimePlanDetailListByUser(addUserId);
        return  dataList;
    }


}
