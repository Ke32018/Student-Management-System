package com.yhp.web;

import com.yhp.bean.Menu;
import com.yhp.bean.Role;
import com.yhp.service.MenuService;
import com.yhp.service.impl.MenuServiceImpl;
import com.yhp.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/menus")
public class MenusServlet extends HttpServlet{
    MenuService menuService = new MenuServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if("select".equals(method)){
            select(req, resp);
        }
    }

    protected void select(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收参数(分页参数+模糊查参数)
        String index = req.getParameter("index");
        int pageIndex=(index==null||index.length()==0)?1:Integer.parseInt(index);
        //2.调取service方法(1.查询数据列表的方法  2.查询总条数的方法)
        PageUtil pageUtil = new PageUtil();
        List<Menu> menuList = menuService.getMenuList(pageIndex, pageUtil.getPageSize());
        int total = menuService.total();
        pageUtil.setTotal(total);
        pageUtil.setPageIndex(pageIndex);
        pageUtil.setDataList(menuList);

        //3.存值跳页面
        req.setAttribute("pi",pageUtil);
        req.getRequestDispatcher("/power/menu/list.jsp").forward(req,resp);


    }
}
