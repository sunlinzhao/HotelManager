package com.coder.hotel.dao.impl;

import com.coder.hotel.dao.MemberLevelDao;
import com.coder.hotel.entity.MemberLevel;
import com.coder.hotel.util.DBUtil;
import com.coder.hotel.util.StringUtil;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author teacher_shi
 */
public class MemberLevelDaoImpl extends BaseDao<MemberLevel>
        implements MemberLevelDao {
    @Override
    public List<MemberLevel> selectByExample(MemberLevel level) {
        String sql="select id,level,low,high,discount from memberlevel where 1=1";
        List<Object> args=new ArrayList<>();
        if (StringUtil.isNotmpty(level.getLevel())){
            sql+=" and level like ?";
            args.add("%"+level.getLevel()+"%");
        }
        if (level.getHigh()!=0){
            sql+=" and high<=?";
            args.add(level.getHigh());
        }
        if (level.getLow()!=0) {
            sql += " and low>=?";
            args.add(level.getLow());
        }
        QueryRunner queryRunner=new QueryRunner(DBUtil.getDataSource());
        try {
            return queryRunner.query(sql,new BeanListHandler<MemberLevel>(MemberLevel.class),args.toArray());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Integer selectByLevel(String level) {
        String sql="select id from memberlevel where level=?";
        QueryRunner queryRunner=new QueryRunner(DBUtil.getDataSource());
        try {
            return queryRunner.query(sql, new ScalarHandler<Integer>(), level);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
