package com.yhp.dao;

import com.yhp.bean.Users;

public interface UsersDao {
    public Users login(String username, String password);
}
