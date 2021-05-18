package com.yhp.web;

import com.yhp.bean.Grade;
import com.yhp.bean.Student;
import com.yhp.service.GradeService;
import com.yhp.service.StudentService;
import com.yhp.service.impl.GradeServiceImpl;
import com.yhp.service.impl.StudentServiceImpl;
import com.yhp.util.PageUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(urlPatterns = "/student")
public class StudentServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if("insert".equals(method)){
            insert(req,resp);
        }else if("update".equals(method)){
            update(req, resp);
        }else if("findById".equals(method)){
            findById(req, resp);
        }else if("delete".equals(method)){
            delete(req, resp);
        }else{
            getList(req, resp);
        }

    }
    //统一调取Service
    StudentService service=new StudentServiceImpl();

    //新增学生
    protected void insert(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String stuNo = req.getParameter("stuNo");
        String stuname = req.getParameter("stuName");
        String gid = req.getParameter("gid");
        String sex = req.getParameter("sex");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String registered = req.getParameter("registered");
        String address = req.getParameter("address");
        String politics = req.getParameter("politics");
        String idnumber = req.getParameter("idNumber");
        String profession = req.getParameter("profession");
        String introdction = req.getParameter("introdction");
        //调取service
        //将参数封装到学生对象中
        Student student = new Student();
        student.setStuNo(stuNo);
        student.setStuName(stuname);
        student.setGid(Integer.parseInt(gid));
        student.setSex(Integer.parseInt(sex));
        student.setEmail(email);
        student.setPhone(phone);
        student.setRegistered(registered);
        student.setAddress(address);
        student.setPolitics(politics);
        student.setIdNumber(idnumber);
        student.setProfession(profession);
        student.setIntrodction(introdction);

        int i = service.insertStu(student);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if(i>0){
            writer.println("<script>alert('新增成功');location.href='student'</script>");
        }else{
            writer.println("<script>alert('新增失败');location.href='getGradeList'</script>");
        }
    }

    //删除学生
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("sid");

        int i = service.deleteById(sid);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if(i>0){
            writer.println("<script>alert('删除成功');location.href='student'</script>");
        }else{
            writer.println("<script>alert('删除失败');location.href='student'</script>");
        }
    }

    //根据id查找
    protected void findById(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GradeService gService = new GradeServiceImpl();
        String sid = req.getParameter("sid");
        Student s = service.findById(Integer.parseInt(sid));
        List<Grade> gradeList = gService.getGradeList();


        req.setAttribute("stu",s);
        req.setAttribute("glist", gradeList);
        req.getRequestDispatcher("/Educational/student/edit.jsp").forward(req,resp);

    }

    //更新学生
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sid = req.getParameter("sid");
        String stuNo = req.getParameter("stuNo");
        String stuname = req.getParameter("stuName");
        String gid = req.getParameter("gid");
        String sex = req.getParameter("sex");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String registered = req.getParameter("registered");
        String address = req.getParameter("address");
        String politics = req.getParameter("politics");
        String idnumber = req.getParameter("idNumber");
        String profession = req.getParameter("profession");
        String introdction = req.getParameter("introdction");
        //调取service
        //将参数封装到学生对象中
        Student student = new Student();
        student.setStuId(Integer.parseInt(sid));
        student.setStuNo(stuNo);
        student.setStuName(stuname);
        student.setGid(Integer.parseInt(gid));
        student.setSex(Integer.parseInt(sex));
        student.setEmail(email);
        student.setPhone(phone);
        student.setRegistered(registered);
        student.setAddress(address);
        student.setPolitics(politics);
        student.setIdNumber(idnumber);
        student.setProfession(profession);
        student.setIntrodction(introdction);

        int i = service.updateStu(student);
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter writer = resp.getWriter();
        if(i>0){
            writer.println("<script>alert('更新成功');location.href='student'</script>");
        }else{
            writer.println("<script>alert('更新失败');location.href='student?method=findById&sid="+sid+"'</script>");
        }

    }

    //获取学生列表
    protected void getList(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取参数
        //1.1 模糊查参数
        String stuName = req.getParameter("stuName");
        String stuNo = req.getParameter("stuNo");
        String sex = req.getParameter("sex");
        System.out.println(stuName);
        System.out.println(stuNo);
        System.out.println(sex);
        //1.2 分页数据
        PageUtil pageUtil = new PageUtil();
        String pageIndex = req.getParameter("pageIndex");
        int index = pageIndex == null ? 1:Integer.parseInt(pageIndex);
        int usex = (sex == null||sex.length()==0 ? -1:Integer.parseInt(sex));
        //调取Service连接数据库获取List
        List<Student> stuList = service.
                getStudents(stuName, stuNo, usex, index, pageUtil.getPageSize());
        //总学生数
        int num = service.count(stuName, stuNo, usex);
        pageUtil.setDataList(stuList);
        pageUtil.setTotal(num);
        pageUtil.setPageIndex(index);
        //将List中的数据传输到前台
        //模糊查询条件
        req.setAttribute("stuName", stuName);
        req.setAttribute("stuNo", stuNo);
        req.setAttribute("sex", sex);
        //分页查数据
        req.setAttribute("p", pageUtil);
        //跳转页面
        req.getRequestDispatcher("/Educational/student/list.jsp").forward(req,resp);

    }
}
