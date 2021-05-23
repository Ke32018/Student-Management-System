package com.yhp.dao;

import com.yhp.bean.Menu;

import java.util.List;

public interface MenuDao {
    //查询菜单列表
    public List<Menu> getMenuList();
    //分页查
    public List<Menu> getMenuList(int index, int size);

    public int total();

}
