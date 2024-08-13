package com.coder.hotel.dao;

import com.coder.hotel.entity.MemberInfo;
import com.coder.hotel.entity.MemberInfoQuery;

import java.util.List;

/**
 * @author teacher_shi
 */
public interface MemberInfoDao {
    int save(MemberInfo info);
    int delete(Object id);
    int update(MemberInfo info);
    MemberInfo selectId(Object id);
    List<MemberInfo> selectList();
    List<MemberInfo> selectByExample(MemberInfoQuery info, int page);
    Object[][] listToArray(List<MemberInfo> list);
    Long getTotal(MemberInfoQuery info);
}
