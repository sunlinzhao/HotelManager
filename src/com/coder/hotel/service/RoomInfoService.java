package com.coder.hotel.service;

import com.coder.hotel.dao.RoomInfoDao;
import com.coder.hotel.dao.impl.RoomInfoDaoImpl;
import com.coder.hotel.entity.RoomInfo;
import com.coder.hotel.util.Page;

import java.util.List;

/**
 * @author teacher_shi
 */
public class RoomInfoService extends BaseService{
    private static RoomInfoDao dao;
    static {
        dao=new RoomInfoDaoImpl();
    }
    public int save(RoomInfo info){
        return dao.save(info);
    }
    public Object[][] selectList(int page){
        List<RoomInfo> roomInfos = dao.selectPage(page);
        return dao.listToArray(roomInfos);
    }
    public Object[][] selectExample(RoomInfo info,int page){
        List<RoomInfo> roomInfos = dao.selectByExample(info,page);
        return dao.listToArray(roomInfos);
    }
    public Page getPage(RoomInfo info,long page){
        //7/5=1  2
        long total=dao.getTotal(info);
        long size=5;
        long pages=total%size==0?total/size:total/size+1;
        return new Page(pages,page,size,total);
    }

    public int deleteId(Object id) {
        return dao.delete(id);
    }

    public RoomInfo selectId(Object id) {
        return dao.selectId(id);
    }

    public int update(RoomInfo info) {
        return dao.update(info);
    }
}
