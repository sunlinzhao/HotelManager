package com.coder.hotel.dao.impl;

import com.coder.hotel.dao.RoomTypeDao;
import com.coder.hotel.entity.RoomType;
import com.coder.hotel.util.DBUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.List;

/**
 * @author teacher_shi
 */
public class RoomTypeDaoImpl extends BaseDao<RoomType> implements RoomTypeDao {
    @Override
    public List<RoomType> selectByType(String type) {
        String sql="select id,type,price,deposit,bednum,remark from roomtype where type like ?";
        DataSource dataSource = DBUtil.getDataSource();
        QueryRunner runner=new QueryRunner(dataSource);
        try {
            return runner.query(sql, new BeanListHandler<RoomType>(RoomType.class),"%"+type+"%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public RoomType selectType(String type) {
        DataSource dataSource = DBUtil.getDataSource();
        QueryRunner runner=new QueryRunner(dataSource);
        String sql="select * from roomtype where type=?";
        try {
            return runner.query(sql,new BeanHandler<RoomType>(RoomType.class),type);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
