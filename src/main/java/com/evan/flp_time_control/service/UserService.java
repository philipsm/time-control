package com.evan.flp_time_control.service;

import com.evan.flp_time_control.dao.UserDAO;
import com.evan.flp_time_control.pojo.AdminRole;
import com.evan.flp_time_control.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;
    @Autowired
    AdminRoleService adminRoleService;

    public List<User> list() {
        List<User> users =  userDAO.list();
        List<AdminRole> roles;
        for (User user : users) {
            roles = adminRoleService.listRolesByUser(user.getUsername());
            user.setRoles(roles);
        }
        return users;
    }

    public boolean isExist(String username) {
        User user = findByUserName(username);
        return null!=user;
    }

    public User findByUserName(String username) {
        return userDAO.findByUsername(username);
    }

    public User get(String username){
        return userDAO.getByUsername(username);
    }

    public void addOrUpdate(User user) {
        userDAO.save(user);
    }
}
