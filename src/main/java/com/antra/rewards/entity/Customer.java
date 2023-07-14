package com.antra.rewards.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "t_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "nick_name")
    private String name;

    @Column(name = "login_name")
    private String loginName;

    @Column(name = "passwd")
    private String password;

    private String salt;

    @Column
    private int points;
    @Column(insertable = false)
    private LocalDateTime createTime;

    public Customer() {
    }

    public Customer(String nickName, String loginName, String password, String salt, int points) {
        this.name = nickName;
        this.loginName = loginName;
        this.password = password;
        this.salt = salt;
        this.points = points;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
