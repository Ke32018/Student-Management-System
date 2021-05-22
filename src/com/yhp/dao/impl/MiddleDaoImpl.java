package com.yhp.dao.impl;

import com.yhp.bean.Menu;
import com.yhp.bean.Role;
import com.yhp.dao.DBUtils;
import com.yhp.dao.MiddleDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MiddleDaoImpl extends DBUtils implements MiddleDao {
    @Override
    public int insertMiddle(int roleid, String[] ids) {
        int k=0;
        try {
            String sql="insert into middle values(null,?,?)";
            // 批量新增 -> 添加事务
            pps = getPps(sql);
            for (String id : ids) {
                pps.setInt(1,roleid);
                pps.setString(2,id);
                pps.addBatch();
            }
            pps.executeBatch();
            k=1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            closeAll();
        }

        return k;
    }



}
