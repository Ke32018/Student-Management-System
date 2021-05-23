package com.yhp.service;

import com.yhp.bean.Role;
import java.util.List;

public interface RoleService {
    public List<Role> getRoleList();
    public List<Role> getRoleList(int pageIndex,int pageSize);
    public int total();
    public Role findById(int roleId);
    public List<Integer> findMenuListById(int roleId);
    public int insertRole(String rolename,String state,String[] ids);
    public int update(Role role,String[] menuids);
    public int delete(int id);
}
