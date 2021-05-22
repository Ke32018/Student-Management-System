package com.yhp.dao;

import com.yhp.bean.Role;

import java.util.List;

public interface RoleDao {
    public List<Role> getRoleList();
    public List<Role> getRoleList(int pageIndex, int pageSize);
    public int total();
    public int insert(Role role);
}
