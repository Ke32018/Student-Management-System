package com.yhp.web;

import com.yhp.bean.Menu;
import com.yhp.bean.Role;
import com.yhp.bean.Users;
import com.yhp.service.MenuService;
import com.yhp.service.RoleService;
import com.yhp.service.UsersService;
import com.yhp.service.impl.MenuServiceImpl;
import com.yhp.service.impl.RoleServiceImpl;
import com.yhp.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/roles"})
public class RoleServlet extends HttpServlet {
    private RoleService roleService=new RoleServiceImpl();
    private MenuService menuService = new MenuServiceImpl();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if("select".equals(method)){
            select(req,resp);
        }else if("selectMenus".equals(method)){
            selectMenus(req,resp);
        }else if("insert".equals(method)){
            insert(req, resp);
        }
    }

    //新增角色
    protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String rolename = req.getParameter("roleName");
        String state = req.getParameter("state");
        String[] menuids = req.getParameterValues("menuid");
        int i = roleService.insertRole(rolename, state, menuids);
        if(i>0){
            resp.sendRedirect("roles?method=select");
        }else{
            resp.sendRedirect("roles?method=selectMenus");
        }

    }

    //查询菜单列表
    protected void selectMenus(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收参数
        //2.调取service方法
        List<Menu> menuList = menuService.getMenuList();
        req.setAttribute("menuList",menuList);
        req.getRequestDispatcher("/power/role/add.jsp").forward(req,resp);

    }

    //查询数据（分页）
    protected void select(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       //1.接收参数(分页参数+模糊查参数)
        String index = req.getParameter("index");
        int pageIndex=(index==null||index.length()==0)?1:Integer.parseInt(index);
       //2.调取service方法(1.查询数据列表的方法  2.查询总条数的方法)
        PageUtil pageUtil = new PageUtil();
        List<Role> roleList = roleService.getRoleList(pageIndex, pageUtil.getPageSize());
        int total = roleService.total();
        pageUtil.setTotal(total);
        pageUtil.setPageIndex(pageIndex);
        pageUtil.setDataList(roleList);

        //3.存值跳页面
        req.setAttribute("pi",pageUtil);
        req.getRequestDispatcher("/power/role/list.jsp").forward(req,resp);



    }


}
