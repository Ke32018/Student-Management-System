package com.yhp.dao.impl;

import com.yhp.bean.Role;
import com.yhp.bean.Users;
import com.yhp.dao.DBUtils;
import com.yhp.dao.UsersDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoImpl extends DBUtils implements UsersDao {
    @Override
    public List<Users> getUsersList(int pageIndex, int pageSize) {
        List<Users> usersList=new ArrayList<Users>();
        try {
            String sql="select userid,loginname,realname,rolename from users u,role r where u.roleid=r.roleid  limit ?,?";
            List params=new ArrayList();
            params.add((pageIndex-1)*pageSize);
            params.add(pageSize);

            resultSet = query(sql, params);
            while(resultSet.next()){
                //1.取出各表的数据
                Users users = new Users();
                users.setUserId(resultSet.getInt("userid"));
                users.setLoginName(resultSet.getString("loginname"));
                users.setRealName(resultSet.getString("realname"));

                Role role = new Role();
                role.setRoleName(resultSet.getString("rolename"));
                //2.建立关系
                users.setRole(role);
                usersList.add(users);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            closeAll();
        }
        return usersList;
    }

    @Override
    public int total() {
        int total =0;
        try {
            String sql="select count(1) from users u,role r where u.roleid=r.roleid ";
            List params=new ArrayList();
            resultSet = query(sql, params);
            while(resultSet.next()){
                //1.取出各表的数据
                total=resultSet.getInt(1);            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            closeAll();
        }
        return total;
    }

    @Override
    public int insertUser(Users u) {
        int result = 0;
        try {
            String sql = "Insert INTO users values(null,?,?,?,?,?,?,?,?,?,?)";
            List params = new ArrayList();
            params.add(u.getLoginName());
            params.add(u.getPassWord());
            params.add(u.getRealName());
            params.add(u.getSex());
            params.add(u.getEmail());
            params.add(u.getAddress());
            params.add(u.getPhone());
            params.add(u.getCardId());
            params.add(u.getDesc());
            params.add(u.getRoleId());

            for(Object o: params) System.out.println(o);
            result = update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return result;
    }

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
                users.setRoleId(resultSet.getInt("roleid"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }

        return users;
    }
}
