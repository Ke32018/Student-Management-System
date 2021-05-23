package com.yhp.dao.impl;

import com.yhp.bean.Menu;
import com.yhp.dao.DBUtils;
import com.yhp.dao.MenuDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDaoImpl extends DBUtils implements MenuDao {
    @Override
    public List<Menu> getMenuList(int pageIndex, int pageSize) {
        List<Menu> menuList=new ArrayList<>();
        try {
            String sql="select * from menu limit ?,?";
            List params=new ArrayList();
            params.add((pageIndex-1)*pageSize);
            params.add(pageSize);

            resultSet = query(sql, params);
            while(resultSet.next()){
                Menu menu = new Menu();
                menu.setMenuId(resultSet.getInt("menuid"));
                menu.setMenuName(resultSet.getString("menuname"));
                menu.setUpmenuId(resultSet.getInt("upmenuid"));
                menu.setState(resultSet.getInt("state"));
                menu.setDesc(resultSet.getString("desc"));
                menu.setUrl(resultSet.getString("url"));
                menuList.add(menu);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            closeAll();
        }
        return menuList;
    }

    @Override
    public int total() {
        int total=0;
        try {
            String sql="select count(1) from menu";
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
    public List<Menu> getMenuList() {
        List<Menu> menuList=new ArrayList<>();
        try {
            String sql="select * from menu";
            resultSet = query(sql, null);
            while(resultSet.next()){
                Menu menu = new Menu();
                menu.setMenuId(resultSet.getInt("menuid"));
                menu.setMenuName(resultSet.getString("menuname"));
                menu.setUpmenuId(resultSet.getInt("upmenuid"));
                menu.setState(resultSet.getInt("state"));
                menu.setDesc(resultSet.getString("desc"));
                menu.setUrl(resultSet.getString("url"));
                menuList.add(menu);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return menuList;
    }
}
