package com.silv.api.dto;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Arrange3To2Dto {
    @NotEmpty(message = "请勾选三位领导")
    @Size(min = 3, max = 3, message = "三班两倒必须选择三位领导") // 值必须为3
    private Integer[] leader;
    @NotEmpty(message = "请勾选三位班长")
    @Size(min = 3, max = 3, message = "三班两倒必须选择三位值班班长")// 值必须为3
    private Integer[] monitor;
    @NotEmpty(message = "请勾选六位员工")
    @Size(min = 6, max = 6, message = "三班两倒必须选择六位员工")// 值必须为6
    private Integer[] staff;
    @NotNull(message = "值班类型不为空")
    @Range(min = 1, max = 3, message = "值班类型只有三种") // 限制1、2、3
    private Integer type;
    @NotNull(message = "排班日期不为空")
    private String date;

    public Integer[] getLeader() {
        return leader;
    }

    public void setLeader(Integer[] leader) {
        this.leader = leader;
    }

    public Integer[] getMonitor() {
        return monitor;
    }

    public void setMonitor(Integer[] monitor) {
        this.monitor = monitor;
    }

    public Integer[] getStaff() {
        return staff;
    }

    public void setStaff(Integer[] staff) {
        this.staff = staff;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
