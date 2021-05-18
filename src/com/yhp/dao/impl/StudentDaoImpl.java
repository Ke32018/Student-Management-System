package com.yhp.dao.impl;

import com.yhp.bean.Student;
import com.yhp.dao.DBUtils;
import com.yhp.dao.StudentDao;
import com.yhp.util.StudentEnum;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StudentDaoImpl extends DBUtils implements StudentDao {
    @Override
    public int count(String stuName, String stuNo, int sex) {
        List params = new ArrayList<>();
        List<Student> stuList = new ArrayList<>();
        StringBuffer sqlbuf = new StringBuffer(" Select * from student where 1=1 and state!=4 ");
        if(stuName!=null && stuName.length()>0){
            sqlbuf.append(" and stuname like ? ");
            params.add("%"+ stuName +"%");
        }
        if(stuNo!=null&&stuNo.length()>0){
            sqlbuf.append(" and stuno = ? ");
            params.add(stuNo);
        }
        if(sex!=-1){
            sqlbuf.append(" and sex = ? ");
            params.add(Integer.toString(sex));
        }
        try {
            ResultSet resultSet = query(sqlbuf.toString(), params);
            while(resultSet.next()){
                Student student = new Student();
                student.setStuId(resultSet.getInt("stuid"));
                student.setStuNo(resultSet.getString("stuno"));
                student.setStuName(resultSet.getString("stuname"));
                student.setSex(resultSet.getInt("sex"));
                student.setPhone(resultSet.getString("phone"));
                student.setProfession(resultSet.getString("profession"));
                student.setRegDate(resultSet.getDate("regdate"));
                //补全所有的列
                stuList.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }

        return stuList.size();
    }

    @Override
    public int insertStu(Student student) {
        int i = 0;
        try {
            String sql = "insert into student values(null,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            List params = new ArrayList();
            params.add(student.getStuName());
            params.add(student.getStuNo());
            params.add(student.getSex());
            params.add(student.getPhone());
            params.add(student.getEmail());
            params.add(student.getRegistered());
            params.add(student.getAddress());
            params.add(student.getProfession());
            params.add(student.getIdNumber());
            params.add(student.getPolitics());
            params.add(new Date());
            params.add(StudentEnum.READING.type); //1代表在读
            params.add(student.getIntrodction());
            params.add(student.getGid());
            for(Object o: params) System.out.println(o);
            i = update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return i;
    }

    @Override
    public Student findById(int id) {
        Student student = new Student();
        String sql = "Select * from student where stuid = ?";
        try {
            List params=new ArrayList();
            params.add(id);

            resultSet=query(sql,params);
            while(resultSet.next()){
                student.setStuId(resultSet.getInt("stuid"));
                student.setStuNo(resultSet.getString("stuno"));
                student.setStuName(resultSet.getString("stuname"));
                student.setSex(resultSet.getInt("sex"));
                student.setPhone(resultSet.getString("phone"));
                student.setProfession(resultSet.getString("profession"));
                student.setAddress(resultSet.getString("address"));
                student.setRegDate(resultSet.getDate("regdate"));
                student.setEmail(resultSet.getString("email"));
                student.setIntrodction(resultSet.getString("introduction"));
                student.setGid(resultSet.getInt("gid"));
                student.setRegistered(resultSet.getString("registered"));
                student.setIdNumber(resultSet.getString("idnumber"));
                student.setPolitics(resultSet.getString("politics"));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return student;
    }

    @Override
    public int deleteById(String sid) {
        int update = 0;
        try {
            String sql="update student  set state=? where stuid=?";
            List params=new ArrayList();
            params.add(StudentEnum.DELETE.type);//4代表退学
            params.add(sid);
            update = update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return update;
    }

    @Override
    public int updateStu(Student student) {
        int update = 0;
        try {
            //这里需要大家自己完善非主键列以外的列的更新
            String sql="update student set stuname=?,address=?,sex=?,phone=?,email=?,registered=?,profession=?,idnumber=?,politics=?,regdate=?,state=?,introduction=?,gid=? where stuid=?";
            List params=new ArrayList();
            params.add(student.getStuName());
            params.add(student.getAddress());
            params.add(student.getSex());
            params.add(student.getPhone());
            params.add(student.getEmail());
            params.add(student.getRegistered());
            params.add(student.getProfession());
            params.add(student.getIdNumber());
            params.add(student.getPolitics());
            params.add(new Date());
            params.add(StudentEnum.READING.type); //1代表在读
            params.add(student.getIntrodction());
            params.add(student.getGid());
            params.add(student.getStuId());

            update = update(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeAll();
        }
        return update;
    }

    @Override
    public List<Student> getStudents(String stuName, String stuNo, int sex, int index, int size) {
        List params = new ArrayList<>();
        List<Student> stuList = new ArrayList<>();
        StringBuffer sqlbuf = new StringBuffer(" Select * from student where 1=1 and state!=4");

        if(stuName!=null && stuName.length()>0){
            sqlbuf.append(" and stuname like ? ");
            params.add("%"+ stuName +"%");
        }
        if(stuNo!=null&&stuNo.length()>0){
            sqlbuf.append(" and stuno = ? ");
            params.add(stuNo);
        }
        if(sex!=-1){
            sqlbuf.append(" and sex = ? ");
            params.add(Integer.toString(sex));
        }
        //分页
        sqlbuf.append(" limit ?,?");
        params.add((index-1)*size);
        params.add(size);
        System.out.println(sqlbuf.toString());
        try {
            ResultSet resultSet = query(sqlbuf.toString(), params);
            while(resultSet.next()){
                Student student = new Student();
                student.setStuId(resultSet.getInt("stuid"));
                student.setStuNo(resultSet.getString("stuno"));
                student.setStuName(resultSet.getString("stuname"));
                student.setSex(resultSet.getInt("sex"));
                student.setPhone(resultSet.getString("phone"));
                student.setProfession(resultSet.getString("profession"));
                student.setRegDate(resultSet.getDate("regdate"));
                //补全所有的列
                stuList.add(student);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }

        return stuList;
    }
}
