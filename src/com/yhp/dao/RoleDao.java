package com.yhp.dao;

import com.yhp.bean.Role;

import java.util.List;

public interface RoleDao {
    public List<Role> getRoleList();
    public List<Role> getRoleList(int pageIndex, int pageSize);
    public int total();
    public Role findById(int id);
    public List<Integer> findMenuListById(int id);
    public int insert(Role role);
    public int update(Role role);
    public int delete(int id);

    /*
    * 用于login时三表联查的findById
    * */
    public Role getRoleById(int id);

}
