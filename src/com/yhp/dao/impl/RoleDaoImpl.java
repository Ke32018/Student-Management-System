package com.yhp.dao.impl;

import com.yhp.bean.Menu;
import com.yhp.bean.Role;
import com.yhp.dao.DBUtils;
import com.yhp.dao.RoleDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl extends DBUtils implements RoleDao {
    @Override
    public List<Integer> findMenuListById(int id) {
        List<Integer> menuIdList = new ArrayList<>();
        String sql = "select menuid from role r, middle m where r.roleid=m.roleid and r.roleid=?;";
        try {
            List params=new ArrayList();
            params.add(id);

            resultSet=query(sql,params);
            while(resultSet.next()){
                menuIdList.add(resultSet.getInt("menuid"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return menuIdList;
    }


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
    public Role getRoleById(int id) {
        Role role = new Role();
        List menuList=new ArrayList();
        try {
            String sql="select * from role r,menu m,middle mid where r.roleid=mid.roleid and mid.menuid=m.menuid and r.roleid=?";
            List params=new ArrayList();
            params.add(id);

            resultSet=query(sql,params);
            while(resultSet.next()){
                role.setRoleId(resultSet.getInt("roleid"));
                role.setRoleName(resultSet.getString("rolename"));
                role.setRoleState(resultSet.getInt("rolestate"));

                Menu menu = new Menu();
                menu.setMenuId(resultSet.getInt("menuid"));
                menu.setMenuName(resultSet.getString("menuname"));
                menu.setUrl(resultSet.getString("url"));
                menu.setState(resultSet.getInt("state"));
                menu.setUpmenuId(resultSet.getInt("upmenuid"));
                menuList.add(menu);
            }
            role.setMenuList(menuList);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return role;
    }

    @Override
    public int delete(int id) {
        int result = 0;
        String sql = "delete from role where roleid=?";
        try {
            List params=new ArrayList();
            params.add(id);

            result = update(sql, params);

        } finally {
            closeAll();
        }
        return result;
    }

    @Override
    public int update(Role role) {
        int i = 0;
        try {
            String sql="update role set rolename=?, rolestate=? where roleid=?";
            List params=new ArrayList();
            params.add(role.getRoleName());
            params.add(role.getRoleState());
            params.add(role.getRoleId());
            i = update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return i;
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

    @Override
    public Role findById(int id) {
        Role role = new Role();
        String sql = "Select * from role where roleid = ?";
        try {
            List params=new ArrayList();
            params.add(id);

            resultSet=query(sql,params);
            while(resultSet.next()){
                role.setRoleId(resultSet.getInt("roleid"));
                role.setRoleName(resultSet.getString("rolename"));
                role.setRoleState(resultSet.getInt("rolestate"));

            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return role;
    }
}
