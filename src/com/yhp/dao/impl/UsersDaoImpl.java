package com.yhp.dao.impl;

import com.yhp.bean.Users;
import com.yhp.dao.DBUtils;
import com.yhp.dao.UsersDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl extends DBUtils implements UsersDao {
    @Override
    public Users login(String username, String password) {
        Users users = null;
        String sql = "Select * from users where loginname=? and password=?";
        List<String> list = new ArrayList<>();
        list.add(username);
        list.add(password);

        try {
            ResultSet resultSet = query(sql, list);
            if(resultSet==null){
                return null;
            }
            while(resultSet.next()){
                users=new Users();
                users.setLoginName(username);
                users.setRealName(resultSet.getString("realname"));
                users.setUserId(resultSet.getInt("userid"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }

        return users;
    }
}
