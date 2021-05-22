package com.yhp.dao.impl;

import com.yhp.bean.Grade;
import com.yhp.bean.Role;
import com.yhp.dao.DBUtils;
import com.yhp.dao.RoleDao;
import com.yhp.service.RoleService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl extends DBUtils implements RoleDao {
    @Override
    public int insert(Role role) {
        int key = 0; //key变量保存新增角色的id
        try {
            String sql="insert into role values(null,?,?)";
            List params=new ArrayList();
            params.add(role.getRoleName());
            params.add(role.getRoleState());
            int  update = update(sql, params);//update变量保存的是受影响的行数

            ResultSet generatedKeys = pps.getGeneratedKeys();
            if(generatedKeys.next()){
                key = generatedKeys.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return key;
    }

    @Override
    public List<Role> getRoleList() {
        Role r = null;
        List params = new ArrayList<>();
        List<Role> roleList = new ArrayList<>();
        try {
            String sql = "Select * from role";

            ResultSet resultSet = query(sql, null);
            while(resultSet.next()) {
                r = new Role();
                r.setRoleId(resultSet.getInt("roleid"));
                r.setRoleName(resultSet.getString("rolename"));
                roleList.add(r);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return roleList;
    }

    @Override
    public List<Role> getRoleList(int pageIndex, int pageSize) {
        List<Role> roleList=new ArrayList<Role>();
        try {
            String sql="select * from role  limit ?,?";
            List params=new ArrayList();
            params.add((pageIndex-1)*pageSize);
            params.add(pageSize);

            resultSet = query(sql, params);
            while(resultSet.next()){
                Role role = new Role();
                role.setRoleId(resultSet.getInt("roleid"));
                role.setRoleName(resultSet.getString("rolename"));
                role.setRoleState(resultSet.getInt("rolestate"));
                roleList.add(role);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            closeAll();
        }
        return roleList;
    }

    @Override
    public int total() {
        int total=0;
        try {
            String sql="select count(1) from role r  ";
            resultSet = query(sql, null);
            while(resultSet.next()){
                total=resultSet.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            closeAll();
        }
        return total;
    }
}
