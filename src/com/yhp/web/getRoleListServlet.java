package com.yhp.web;

import com.yhp.bean.Role;
import com.yhp.service.RoleService;
import com.yhp.service.impl.RoleServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/getRoleList")
public class getRoleListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoleService rService = new RoleServiceImpl();
        List<Role> roleList = rService.getRoleList();

        //给前台赋值
        req.setAttribute("roleList", roleList);
        req.getRequestDispatcher("/power/user/add.jsp").forward(req,resp);
    }
}
