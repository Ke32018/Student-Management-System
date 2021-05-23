package com.yhp.web;

import com.yhp.bean.Menu;
import com.yhp.bean.Role;
import com.yhp.service.MenuService;
import com.yhp.service.RoleService;
import com.yhp.service.impl.MenuServiceImpl;
import com.yhp.service.impl.RoleServiceImpl;
import com.yhp.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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
        }else if("findbyid".equals(method)){
            //findbyid实际上对应是edit.jsp
            //在Servlet中获取了role的信息和menuIdList
            findById(req, resp);
        }else if("update".equals(method)){
            update(req, resp);
        }else if("delete".equals(method)){
            delete(req, resp);
        }
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roleId = req.getParameter("roleid");
        int i = roleService.delete(Integer.parseInt(roleId));
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();

        if(i>0){
            writer.println("<script>alert('删除成功');location.href='roles?method=select'</script>");
        }else{
            writer.println("<script>alert('删除失败');location.href='roles?method=select'</script>");
        }
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roleName = req.getParameter("rolename");
        String state = req.getParameter("state");
        String roleId = req.getParameter("roleid");
        String[] menuids = req.getParameterValues("menuid");



        Role role = new Role();
        role.setRoleState(Integer.parseInt(state));
        role.setRoleName(roleName);
        role.setRoleId(Integer.parseInt(roleId));
        int i = roleService.update(role, menuids);


        if(i>0){
            resp.sendRedirect("roles?method=select");
        }else{
            resp.sendRedirect("roles?method=findbyid");
        }

    }

    protected void findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String roleId = req.getParameter("roleid");

        Role role = roleService.findById(Integer.parseInt(roleId));
        List<Integer> menuIdList = roleService.findMenuListById(Integer.parseInt(roleId));

        req.setAttribute("role", role);
        req.setAttribute("roleid", roleId);
        for (Integer i : menuIdList) {
            if(i==1) req.setAttribute("power", i);
            if(i==2) req.setAttribute("stu",i);
            if(i==3) req.setAttribute("r", i);
            if(i==4) req.setAttribute("menu",i);
            if(i==5) req.setAttribute("person", i);
            if(i==6) req.setAttribute("edu",i);

        }
        req.getRequestDispatcher("/power/role/edit.jsp").forward(req,resp);

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
