package com.evan.flp_time_control.dao;

import com.evan.flp_time_control.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserDAO extends JpaRepository<User,Integer> {

    User findByUsername(String username);

    //@Query(value = "select id,username from user where username = ?1 and password = ?2 ",nativeQuery = true)
    User getByUsername(String username);

    @Query(value = "select new User(u.id,u.username,u.name,u.phone,u.email,u.enabled) from User u")
    List<User> list();
}
