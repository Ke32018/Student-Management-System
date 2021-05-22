package com.yhp.service.impl;

import com.yhp.bean.Users;
import com.yhp.dao.UsersDao;
import com.yhp.dao.impl.UsersDaoImpl;
import com.yhp.service.UsersService;

import java.util.List;

public class UserServiceImpl implements UsersService {
    UsersDao dao = new UsersDaoImpl();

    @Override
    public List<Users> getUsersList(int pageIndex, int pageSize) {
        return dao.getUsersList(pageIndex, pageSize);
    }

    @Override
    public int insertUser(Users u){
        return dao.insertUser(u);
    }

    @Override
    public int total() {
        return dao.total();
    }

    @Override
    public Users login(String username, String password) {
        return dao.login(username, password);
    }
}
