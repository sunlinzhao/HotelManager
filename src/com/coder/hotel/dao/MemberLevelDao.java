package com.coder.hotel.dao;

import com.coder.hotel.entity.MemberLevel;

import java.util.List;

/**
 * @author teacher_shi
 */
public interface MemberLevelDao {
    int save(MemberLevel level);
    int delete(Object id);
    int update(MemberLevel level);
    MemberLevel selectId(Object id);
    List<MemberLevel> selectList();
    List<MemberLevel> selectByExample(MemberLevel level);
    Object[][] listToArray(List<MemberLevel> list);
    Integer selectByLevel(String level);
}
