package com.yhp.dao;

import com.yhp.bean.Role;

public interface MiddleDao {
    //新增角色
    public int insertMiddle(int roleid,String[] ids);

    //删除角色菜单
    public int deleteMiddle(int roleid);

}
