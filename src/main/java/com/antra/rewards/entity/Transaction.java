package com.antra.rewards.entity;

import com.antra.rewards.utils.RewardsUtil;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "t_transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long customerId;
    private int amount;
    private int points;

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Column(insertable = false)
    private LocalDateTime createTime;

    private LocalDateTime transactionTime;

    public Transaction() {
    }

    public Transaction(long customerId, int amount, LocalDateTime transactionTime) {
        this.customerId = customerId;
        this.amount = amount;
        this.transactionTime = transactionTime;
        this.points = RewardsUtil.calculatePoints(amount);
    }

    public long getId() {
        return id;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }
}
