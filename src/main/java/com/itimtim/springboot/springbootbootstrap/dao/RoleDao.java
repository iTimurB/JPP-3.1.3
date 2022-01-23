package com.itimtim.springboot.springbootbootstrap.dao;

import com.itimtim.springboot.springbootbootstrap.model.Role;

import java.util.List;

public interface RoleDao {

    List<Role> getRolesByIds (List<Long> ids);
}
