package com.silv.api.model;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "sms")
public class Sms {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "message_code")
    private String messageCode;

    private String phone;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "expire_time")
    private Timestamp expireTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMessageCode() {
        return messageCode;
    }

    public void setMessageCode(String messageCode) {
        this.messageCode = messageCode;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Timestamp expireTime) {
        this.expireTime = expireTime;
    }
}
