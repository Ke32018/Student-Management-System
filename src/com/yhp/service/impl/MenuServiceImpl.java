package com.yhp.service.impl;

import com.yhp.bean.Menu;
import com.yhp.dao.MenuDao;
import com.yhp.dao.impl.MenuDaoImpl;
import com.yhp.service.MenuService;

import java.util.ArrayList;
import java.util.List;

public class MenuServiceImpl implements MenuService {
    @Override
    public List<Menu> getMenuList() {
        MenuDao menuDao = new MenuDaoImpl();
        List<Menu> menuList = menuDao.getMenuList();
        List<Menu> newList = new ArrayList<>();
        for (Menu m : menuList) {
            //区分一二级menu，一级menu被加入newList被返回
            if(m.getUpmenuId()==0){
                List<Menu> secondMenuList = new ArrayList<>();
                for (Menu second : menuList) {
                    if(second.getUpmenuId() == m.getMenuId()) secondMenuList.add(second);
                }
                m.setSecondMenuList(secondMenuList);
                newList.add(m);
            }
        }
        return newList;
    }
}
