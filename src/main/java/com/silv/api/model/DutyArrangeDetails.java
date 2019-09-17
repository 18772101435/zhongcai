package com.silv.api.model;

public class DutyArrangeDetails {

    private int id;

    private int dutyId;

    private Integer leaderId;

    private Integer monitorId;

    private String staffId;

    private int batch; // 对应着该月份的第几天

    private int type; // 1：早班 2：晚班 3：休息

    private boolean isExpire; // 0：已失效 1：未失效

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDutyId() {
        return dutyId;
    }

    public void setDutyId(int dutyId) {
        this.dutyId = dutyId;
    }

    public Integer getLeaderId() {
        return leaderId;
    }

    public void setLeaderId(Integer leaderId) {
        this.leaderId = leaderId;
    }

    public Integer getMonitorId() {
        return monitorId;
    }

    public void setMonitorId(Integer monitorId) {
        this.monitorId = monitorId;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public int getBatch() {
        return batch;
    }

    public void setBatch(int batch) {
        this.batch = batch;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isExpire() {
        return isExpire;
    }

    public void setExpire(boolean expire) {
        isExpire = expire;
    }

    @Override
    public String toString() {
        return "DutyArrangeDetails{" +
                "id=" + id +
                ", dutyId=" + dutyId +
                ", leaderId='" + leaderId + '\'' +
                ", monitorId='" + monitorId + '\'' +
                ", staffId='" + staffId + '\'' +
                ", batch=" + batch +
                ", type=" + type +
                ", isExpire=" + isExpire +
                '}';
    }
}
