package com.coder.hotel.dao;

import com.coder.hotel.entity.RoomType;

import java.util.List;

/**
 * @author teacher_shi
 */
public interface RoomTypeDao {
    int save(RoomType type);
    int update(RoomType type);
    int delete(Object id);
    RoomType selectId(Object id);
    List<RoomType> selectList();
    List<RoomType> selectByType(String type);
    Object[][] listToArray(List<RoomType> list);

    RoomType selectType(String type);
}
