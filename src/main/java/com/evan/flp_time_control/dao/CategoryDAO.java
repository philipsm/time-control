package com.evan.flp_time_control.dao;


import com.evan.flp_time_control.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category, Integer> {

}
