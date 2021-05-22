package com.yhp.service;

import com.yhp.bean.Role;
import java.util.List;

public interface RoleService {
    public List<Role> getRoleList();
    public List<Role> getRoleList(int pageIndex,int pageSize);
    public int total();
    public int insertRole(String rolename,String state,String[] ids);
}
