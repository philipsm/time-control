package com.evan.flp_time_control.service;

import com.evan.flp_time_control.dao.AdminPermissionDAO;
import com.evan.flp_time_control.pojo.AdminPermission;
import com.evan.flp_time_control.pojo.AdminRole;
import com.evan.flp_time_control.pojo.AdminRolePermission;
import com.evan.flp_time_control.pojo.AdminUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Evan
 * @date 2019/11
 */

@Service
public class AdminPermissionService {
    @Autowired
    AdminPermissionDAO adminPermissionDAO;
    @Autowired
    AdminUserRoleService adminUserRoleService;
    @Autowired
    AdminRoleService adminRoleService;
    @Autowired
    AdminRolePermissionService adminRolePermissionService;
    @Autowired
    UserService userService;

    public AdminPermission findById(int id) {
        return adminPermissionDAO.findById(id);
    }

    public List<AdminPermission> list() {return adminPermissionDAO.findAll();}

    public boolean needFilter(String requestAPI) {
        List<AdminPermission> ps = adminPermissionDAO.findAll();
        for (AdminPermission p: ps) {
            // 这里我们进行前缀匹配，拥有父权限就拥有所有子权限
            if (requestAPI.startsWith(p.getUrl())) {
                return true;
            }
        }
        return false;
    }

    public List<AdminPermission> listPermsByRoleId(int rid) {
        List<AdminRolePermission> rps = adminRolePermissionService.findAllByRid(rid);
        List<AdminPermission> perms = new ArrayList<>();
        for (AdminRolePermission rp : rps) {
            perms.add(adminPermissionDAO.findById(rp.getPid()));
        }
        return perms;
    }

    public Set<String> listPermissionURLsByUser(String username) {
//        List<AdminRole> roles = adminRoleService.listRolesByUser(username);
//        Set<String> URLs = new HashSet<>();
//
//        for (AdminRole role : roles) {
//            List<AdminRolePermission> rps = adminRolePermissionService.findAllByRid(role.getId());
//            for (AdminRolePermission rp : rps) {
//                URLs.add(adminPermissionDAO.findById(rp.getPid()).getUrl());
//            }
//        }
//        return URLs;

        int uid =  userService.findByUserName(username).getId();
        List<AdminRole> roles = new ArrayList<>();
        List<AdminPermission> permissions = new ArrayList<>();
        Set<String> URLs = new HashSet<>();

        List<AdminUserRole> urs = adminUserRoleService.listAllByUid(uid);
        for (AdminUserRole ur: urs) {
            roles.add(adminRoleService.findById(ur.getRid()));
        }

        for (AdminRole role : roles) {
            List<AdminRolePermission> rps = adminRolePermissionService.findAllByRid(role.getId());
            for (AdminRolePermission rp : rps) {
                URLs.add(adminPermissionDAO.findById(rp.getPid()).getUrl());
            }
        }
        return URLs;
    }
}
