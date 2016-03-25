package com.xsx.weather;

/**
 * 温度
 * Created by XSX on 2016/3/6.
 */
public class Temp {

    /**
     * 最高温度
     */
    private int ht;
    /**
     * 最低温度
     */
    private int lt;

    public Temp() {
    }

    public Temp(int ht, int lt) {
        this.ht = ht;
        this.lt = lt;
    }

    public int getHt() {
        return ht;
    }

    public void setHt(int ht) {
        this.ht = ht;
    }

    public int getLt() {
        return lt;
    }

    public void setLt(int lt) {
        this.lt = lt;
    }
}
