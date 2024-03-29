package com.yhp.service;

import com.yhp.bean.Menu;

import java.util.List;

public interface MenuService {
    //查询菜单列表
    public List<Menu> getMenuList();
    //分页查询菜单列表
    public List<Menu> getMenuList(int index, int size);

    public int total();

}
