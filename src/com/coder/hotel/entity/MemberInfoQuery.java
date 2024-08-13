package com.coder.hotel.entity;

/**
 * @author teacher_shi
 */
public class MemberInfoQuery extends MemberInfo {
    private Integer lowAge;
    private Integer highAge;

    public Integer getLowAge() {
        return lowAge;
    }

    public void setLowAge(Integer lowAge) {
        this.lowAge = lowAge;
    }

    public Integer getHighAge() {
        return highAge;
    }

    public void setHighAge(Integer highAge) {
        this.highAge = highAge;
    }
}
