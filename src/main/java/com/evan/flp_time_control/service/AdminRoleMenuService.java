package com.evan.flp_time_control.service;

import com.evan.flp_time_control.dao.AdminRoleMenuDAO;
import com.evan.flp_time_control.pojo.AdminRoleMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Evan
 * @date 2019/11
 */

@Service
public class AdminRoleMenuService {
    @Autowired
    AdminRoleMenuDAO adminRoleMenuDAO;

    public List<AdminRoleMenu> findAllByRid(int rid) {
        return adminRoleMenuDAO.findAllByRid(rid);
    }

    @Modifying
    @Transactional
    public void deleteAllByRid(int rid) {
        adminRoleMenuDAO.deleteAllByRid(rid);
    }

    public void save(AdminRoleMenu rm) {
        adminRoleMenuDAO.save(rm);
    }
}
