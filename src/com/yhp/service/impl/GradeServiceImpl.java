package com.yhp.service.impl;

import com.yhp.bean.Grade;
import com.yhp.dao.GradeDao;
import com.yhp.dao.impl.GradeDaoImpl;
import com.yhp.service.GradeService;

import java.util.List;

public class GradeServiceImpl implements GradeService {
    @Override
    public List<Grade> getGradeList() {
        GradeDao dao = new GradeDaoImpl();
        return dao.getGradeList();
    }
}
