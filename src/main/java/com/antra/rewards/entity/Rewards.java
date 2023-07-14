package com.antra.rewards.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_rewards")
public class Rewards {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column
    private long customerId;
    @Column
    private int points;
    @Column(name = "reward_year")
    private int year;
    @Column(name = "reward_month")
    private int month;

    @Column(insertable = false)
    private LocalDateTime createTime;


    public long getId() {
        return id;
    }

    public Rewards() {
    }

    public Rewards(long customerId, int points, int year, int month) {
        this.customerId = customerId;
        this.points = points;
        this.year = year;
        this.month = month;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }
}
