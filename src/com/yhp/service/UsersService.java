package com.yhp.service;

import com.yhp.bean.Users;

import java.util.List;

public interface UsersService {
    /**
     * 登录方法
     */
    public Users login(String username,String password);
    /**
     * 查询用户列表
     */
    public List<Users> getUsersList(int pageIndex,int pageSize);
    /**
     * 查询总条数
     */
    public int total();

    public int insertUser(Users u);
}
