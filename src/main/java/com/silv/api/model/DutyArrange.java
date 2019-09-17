package com.silv.api.model;

import java.sql.Timestamp;
import java.util.Date;

public class DutyArrange {

    private int id;

    private Date dutyDate;

    private int type; // 1：3班2倒 2：4班3倒 3：5休2

    private boolean isExpire; // 0：已失效 1：未失效

    private int flag; // 0：自动排班 1：手动选择某一天排班

    private Timestamp createTime;

    private Timestamp updateTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDutyDate() {
        return dutyDate;
    }

    public void setDutyDate(Date dutyDate) {
        this.dutyDate = dutyDate;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isIsExpire() {
        return isExpire;
    }

    public void setIsExpire(boolean isExpire) {
        this.isExpire = isExpire;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
}
