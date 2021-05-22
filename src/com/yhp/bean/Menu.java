package com.yhp.bean;

import java.util.List;

public class Menu {
    private Integer menuId;
    private String menuName;
    private Integer upmenuId;
    private Integer state;
    private String desc;
    private String url;
    private List<Role> roleList;
    private List<Menu> secondMenuList;//保存二级目录

    public List<Menu> getSecondMenuList() {
        return secondMenuList;
    }

    public void setSecondMenuList(List<Menu> secondMenuList) {
        this.secondMenuList = secondMenuList;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getUpmenuId() {
        return upmenuId;
    }

    public void setUpmenuId(Integer upmenuId) {
        this.upmenuId = upmenuId;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }
}
