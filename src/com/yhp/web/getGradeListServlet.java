package com.yhp.web;

import com.yhp.bean.Grade;
import com.yhp.service.GradeService;
import com.yhp.service.impl.GradeServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/getGradeList")
public class getGradeListServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GradeService service = new GradeServiceImpl();
        List<Grade> gradeList = service.getGradeList();
        req.setAttribute("gradeList", gradeList);
        //转发到add.jsp
        req.getRequestDispatcher("/Educational/student/add.jsp").forward(req,resp);
    }
}
