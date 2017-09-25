package com.silv.api.model;

import javax.persistence.*;

/**
 * Created by yao on 2017/9/25.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /**
     * 用户名
     **/
    @Column(name = "name", columnDefinition = "NVARCHAR(255)")
    private String name;

    /**
     * 密码
     **/
    @Column(name = "password", columnDefinition = "NVARCHAR(255)")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    public User() {
    }
}
