package com.yhp.web;

import com.yhp.bean.Users;
import com.yhp.service.UsersService;
import com.yhp.service.impl.UserServiceImpl;
import com.yhp.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


@WebServlet(urlPatterns = "/users")
public class UsersServlet extends HttpServlet {
    UsersService usersService = new UserServiceImpl();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if("select".equals(method)){
            select(req, resp);
        }if("insert".equals(method)){
            insert(req, resp);
        }
    }

    protected void select(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收参数(分页参数+模糊查参数)
        String index = req.getParameter("index");
        int pageIndex=(index==null||index.length()==0)?1:Integer.parseInt(index);
        //2.分页查询数据
        PageUtil pageUtil = new PageUtil();
        List<Users> usersList = usersService.getUsersList(pageIndex, pageUtil.getPageSize());
        int total = usersService.total();
        pageUtil.setPageIndex(pageIndex);
        pageUtil.setTotal(total);
        pageUtil.setDataList(usersList);
        //3.传值，跳转页面.
        req.setAttribute("p",pageUtil);
        req.getRequestDispatcher("/power/user/list.jsp").forward(req,resp);
    }


    protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.接收参数
        String loginName = req.getParameter("loginName");
        String password = req.getParameter("password");
        String realName = req.getParameter("realName");
        String sex = req.getParameter("sex");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String roleId = req.getParameter("roleId");
        String address = req.getParameter("address");
        String cardId = req.getParameter("cardId");
        String desc = req.getParameter("desc");
        //调取service
        //将参数封装到user对象中
        Users u = new Users();

        u.setLoginName(loginName);
        u.setPassWord(password);
        u.setRealName(realName);
        u.setSex(Integer.parseInt(sex));
        u.setEmail(email);
        u.setPhone(phone);
        u.setAddress(address);
        u.setRoleId(Integer.parseInt(roleId));
        u.setCardId(cardId);
        u.setDesc(desc);

        //3.传值，跳转页面.
        int i = usersService.insertUser(u);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if(i>0){
            writer.println("<script>alert('新增成功');location.href='users?method=select'</script>");
        }else{
            writer.println("<script>alert('新增失败');location.href='getRoleList'</script>");
        }
    }
}
