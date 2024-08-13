package com.coder.hotel.dao.impl;

import com.coder.hotel.dao.AdminDao;
import com.coder.hotel.entity.Admin;
import com.coder.hotel.util.DBUtil;
import com.coder.hotel.util.MD5Util;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author teacher_shi
 */
public class AdminDaoImpl extends BaseDao<Admin> implements AdminDao {
    @Override
    public Admin login(String name, String pwd) {
        String sql="select id,name,pwd,lasttime from admin where name=? and pwd=?";
        QueryRunner runner=new QueryRunner(DBUtil.getDataSource());
        Admin admin = null;
        try {
            admin = runner.query(sql, new BeanHandler<>(Admin.class), name, MD5Util.getPWD(pwd));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return admin;
    }

    @Override
    public List<Admin> selectByName(String name) {
        return null;
    }
}
