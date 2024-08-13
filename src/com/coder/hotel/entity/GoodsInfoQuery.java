package com.coder.hotel.entity;

/**
 * @author teacher_shi
 */
public class GoodsInfoQuery extends GoodsInfo{
    private Integer lowPrice;
    private Integer highPrice;

    public Integer getLowPrice() {
        return lowPrice;
    }

    public void setLowPrice(Integer lowPrice) {
        this.lowPrice = lowPrice;
    }

    public Integer getHighPrice() {
        return highPrice;
    }

    public void setHighPrice(Integer highPrice) {
        this.highPrice = highPrice;
    }
}
