package com.yhp.service.impl;

import com.yhp.bean.Student;
import com.yhp.dao.StudentDao;
import com.yhp.dao.impl.StudentDaoImpl;
import com.yhp.service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    StudentDao dao = new StudentDaoImpl();

    @Override
    public Student findById(int id) {
        return dao.findById(id);
    }

    @Override
    public int updateStu(Student s) {
        return dao.updateStu(s);
    }

    @Override
    public int deleteById(String sid) {
        return dao.deleteById(sid);
    }

    @Override
    public List<Student> getStudents(String stuName, String stuNo, int sex, int index, int size) {
        return dao.getStudents(stuName, stuNo, sex, index, size);
    }

    @Override
    public int count(String stuName, String stuNo, int sex) {
        return dao.count(stuName, stuNo, sex);
    }

    @Override
    public int insertStu(Student s) {
        return dao.insertStu(s);
    }
}
