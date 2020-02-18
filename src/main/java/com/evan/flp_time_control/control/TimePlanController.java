package com.evan.flp_time_control.control;

import com.evan.flp_time_control.pojo.TimePlan;
import com.evan.flp_time_control.pojo.TimePlanDetail;
import com.evan.flp_time_control.pojo.User;
import com.evan.flp_time_control.service.TimePlanDetailService;
import com.evan.flp_time_control.service.TimePlanService;
import com.evan.flp_time_control.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class TimePlanController {

    @Autowired
    TimePlanService timePlanService;

    @Autowired
    TimePlanDetailService timePlanDetailService;

    @Autowired
    UserService userService;
    /**
     * 查询用户所有计划
     * @param user
     * @return
     */
    @PostMapping("api/timeplan/getTimePlanList")
    public List<TimePlan> findAllByAddUser(@RequestBody User user){
        int userId = user.getId();
        List<TimePlan> timePlanList = timePlanService.getTimePlanListByUser(userId);
        return timePlanList;
    }

    /**
     * 添加数据
     * @param timePlan
     * @return
     */
    @PostMapping("api/timeplan/addTimePlan")
    public TimePlan addTimePlan(@RequestBody TimePlan timePlan){
        //自动填充
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        timePlan.setAddTime(time);
        timePlan.setModifyTime(time);

        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.findByUserName(username);

        timePlan.setAddUser(user.getId());
        timePlan.setModifyUser(user.getId());

        TimePlan result = timePlanService.addOrUpdate(timePlan);
        return result;
    }

    /**
     * 根据ID删除数据
     */
    @PostMapping("api/timeplan/deleteByTimePlanId")
    public int deleteByTimePlanId(@RequestBody TimePlan timePlan){
        int timePlanId = timePlan.getTimePlanId();
        int result = timePlanService.deleteById(timePlanId);
        return result;
    }

    /**
     * 根据添加用户查询对应的所有计划详情
     */
    @GetMapping("api/timeplan/getTimePlanDetailListByUser")
    public List<TimePlanDetail> getTimePlanDetailListByUser(){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.findByUserName(username);
        List<TimePlanDetail> dataList = timePlanDetailService.getTimePlanDetailListByUser(user.getId());
        dataList.forEach(item->{
            item.setTimeSlice(item.getStartTime()+"～"+item.getEndTime());
        });

        return dataList;
    }

    /**
     * 添加计划详情
     */
    //@CrossOrigin
    @PostMapping("api/timeplan/savePlanDetail")
    public TimePlanDetail savePlanDetail(@RequestBody TimePlanDetail timePlanDetail){
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        User user = userService.findByUserName(username);
        timePlanDetail.setAddUser(user.getId());
        timePlanDetail.setModifyUser(user.getId());

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        timePlanDetail.setAddTime(time);
        timePlanDetail.setModifyTime(time);

        TimePlanDetail result = timePlanDetailService.addOrUpdate(timePlanDetail);
        result.setTimeSlice(timePlanDetail.getStartTime()+"～"+timePlanDetail.getEndTime());
        return result;
    }

    /**
     * 删除计划详情
     */
    @PostMapping("api/timeplan/deletePlanDetail")
    public int deletePlanDetail(@RequestBody TimePlanDetail timePlanDetail){
        int result = timePlanDetailService.deleteByTimePlanDetailId(timePlanDetail.getTimePlanDetailId());
        return result;
    }
}
