package com.coder.hotel.entity;

import com.coder.hotel.util.TableId;

/**
 * @author teacher_shi
 */
public class GoodsType {
    @TableId
    private Integer id;
    private String typeName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
