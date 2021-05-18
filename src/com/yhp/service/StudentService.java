package com.yhp.service;

import com.yhp.bean.Student;

import java.util.List;

public interface StudentService {
    public List<Student> getStudents(String stuName, String stuNo, int sex, int index, int size);

    public int count(String stuName, String stuNo, int sex);

    public int insertStu(Student s);

    public Student findById(int id);

    public int deleteById(String sid);

    public int updateStu(Student s);

}
