package com.yhp.web;


import com.yhp.bean.Users;
import com.yhp.service.UsersService;
import com.yhp.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet{
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.调取参数
        String username = req.getParameter("TxtUserName");
        String password = req.getParameter("TxtPassword");
        //2.调取Service
        UsersService service = new UserServiceImpl();
        Users u = service.login(username, password);
        //3.跳转页面
        if(u != null){
            //保存用户名信息，u1代表获取的用户对象，可以在session中使用el表达式获取其值
            req.getSession().setAttribute("u1",u);
            resp.sendRedirect("index.jsp");
        }else {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter writer = resp.getWriter();
            writer.println("<script>location.href='login.jsp';alert('用户名或密码不正确');</script>");
        }
    }
}
