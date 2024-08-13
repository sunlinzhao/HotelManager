package com.coder.hotel.dao;

import com.coder.hotel.entity.RoomInfo;

import java.util.List;

/**
 * @author teacher_shi
 */
public interface RoomInfoDao {
    int save(RoomInfo info);
    int update(RoomInfo info);
    int delete(Object id);
    RoomInfo selectId(Object id);
    List<RoomInfo> selectList();
    List<RoomInfo> selectByExample(RoomInfo info,int page);
    Object[][] listToArray(List<RoomInfo> list);
    List<RoomInfo> selectPage(int page);
    Long getTotal(RoomInfo info);
}
