package com.yhp.dao.impl;

import com.yhp.bean.Grade;
import com.yhp.bean.Student;
import com.yhp.dao.DBUtils;
import com.yhp.dao.GradeDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GradeDaoImpl extends DBUtils implements GradeDao {
    @Override
    public List<Grade> getGradeList() {
        Grade g = null;
        List params = new ArrayList<>();
        List<Grade> graList = new ArrayList<>();
        String sql = "Select * from grade";
        try {
            ResultSet resultSet = query(sql, null);
            while(resultSet.next()){
                g = new Grade();
                g.setGradeId(resultSet.getInt("gradeid"));
                g.setGradeName(resultSet.getString("gradename"));
                graList.add(g);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }
        return graList;
    }
}
