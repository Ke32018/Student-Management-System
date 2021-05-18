package com.yhp.service.impl;

import com.yhp.bean.Users;
import com.yhp.dao.UsersDao;
import com.yhp.dao.impl.UsersDaoImpl;
import com.yhp.service.UsersService;

public class UserServiceImpl implements UsersService {
    @Override
    public Users login(String username, String password) {
        UsersDao dao = new UsersDaoImpl();
        return dao.login(username, password);
    }
}
