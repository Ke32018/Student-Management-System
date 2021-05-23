package com.yhp.service.impl;

import com.yhp.bean.Menu;
import com.yhp.bean.Role;
import com.yhp.bean.Users;
import com.yhp.dao.RoleDao;
import com.yhp.dao.UsersDao;
import com.yhp.dao.impl.RoleDaoImpl;
import com.yhp.dao.impl.UsersDaoImpl;
import com.yhp.service.UsersService;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UsersService {
    UsersDao dao = new UsersDaoImpl();
    RoleDao roleDao = new RoleDaoImpl();

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
        Users users = dao.login(username, password);
        if(users == null) return null;
        Integer userId = users.getUserId();
        //根据角色id查询角色，菜单信息(三表联查)
        Integer roleId = users.getRoleId();
        System.out.println(roleId);
        Role role = roleDao.getRoleById(roleId);
        //需要对菜单进行分级
        List<Menu> menuList = role.getMenuList();
        List<Menu> newMenuList=new ArrayList<>();//保存分级以后的菜单
        for (Menu menu : menuList) {
            if(menu.getUpmenuId()==0){ //说明是一级菜单
                List<Menu> secondList = new ArrayList<>();
                for (Menu second : menuList) {
                    if(second.getUpmenuId()==menu.getMenuId()){
                        secondList.add(second);
                    }
                }
                menu.setSecondMenuList(secondList);
                newMenuList.add(menu);
            }
        }
        role.setMenuList(newMenuList);
        users.setRole(role);

        return users;
    }
}
