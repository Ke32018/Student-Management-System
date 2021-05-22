package com.yhp.service.impl;

import com.yhp.bean.Role;
import com.yhp.dao.MiddleDao;
import com.yhp.dao.RoleDao;
import com.yhp.dao.impl.MiddleDaoImpl;
import com.yhp.dao.impl.RoleDaoImpl;
import com.yhp.service.RoleService;

import java.util.List;

public class RoleServiceImpl implements RoleService {
    RoleDao roleDao = new RoleDaoImpl();
    MiddleDao middleDao = new MiddleDaoImpl();

    @Override
    public int insertRole(String rolename, String state, String[] ids) {
        int k=0;
        try {
            //新增角色并获取其id
            Role role = new Role();
            role.setRoleName(rolename);
            role.setRoleState(Integer.parseInt(state));
            int id = roleDao.insert(role);

            //更新角色菜单权限(middle表)
            middleDao.insertMiddle(id, ids);
            k=1;
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return k;
    }

    @Override
    public List<Role> getRoleList() {
        return roleDao.getRoleList();
    }

    @Override
    public List<Role> getRoleList(int pageIndex, int pageSize) {
        return roleDao.getRoleList(pageIndex, pageSize);
    }

    @Override
    public int total() {
        return roleDao.total();
    }

}
