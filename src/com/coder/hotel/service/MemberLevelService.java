package com.coder.hotel.service;

import com.coder.hotel.dao.MemberLevelDao;
import com.coder.hotel.dao.impl.MemberLevelDaoImpl;
import com.coder.hotel.entity.MemberLevel;

import java.util.List;

/**
 * @author teacher_shi
 */
public class MemberLevelService extends BaseService{
    private static MemberLevelDao dao;
    static {
        dao=new MemberLevelDaoImpl();
    }
    public List<MemberLevel> getList(){
        return dao.selectList();
    }
    public Object[][] selectList(){
        List<MemberLevel> memberLevels = dao.selectList();
        return dao.listToArray(memberLevels);
    }
    public Object[][] selectExample(MemberLevel level){
        List<MemberLevel> memberLevels = dao.selectByExample(level);
        return dao.listToArray(memberLevels);
    }

    public int deleteId(Object id) {
        return dao.delete(id);
    }

    public int save(MemberLevel memberLevel) {
        return dao.save(memberLevel);
    }

    public MemberLevel selectId(Object id) {
        return dao.selectId(id);
    }

    public int update(MemberLevel memberLevel) {
        return dao.update(memberLevel);
    }

    public Integer selectByLevel(String level) {
        return dao.selectByLevel(level);
    }
}
