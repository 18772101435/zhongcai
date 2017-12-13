package com.silv.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.sql.Timestamp;

@Entity
public class Email {
  @Id
  @GeneratedValue
  private Long id;

  @Column(name = "receive_email")
  private String receiveEmail;

  @Column(name = "send_email")
  private String sendEmail;

  private String content;

  @Column(name = "create_time")
  private Timestamp createTime;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getReceiveEmail() {
    return receiveEmail;
  }

  public void setReceiveEmail(String receiveEmail) {
    this.receiveEmail = receiveEmail;
  }

  public String getSendEmail() {
    return sendEmail;
  }

  public void setSendEmail(String sendEmail) {
    this.sendEmail = sendEmail;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Timestamp getCreateTime() {
    return createTime;
  }

  public void setCreateTime(Timestamp createTime) {
    this.createTime = createTime;
  }
}
