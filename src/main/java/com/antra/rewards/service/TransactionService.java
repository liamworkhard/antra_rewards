package com.antra.rewards.service;

import com.antra.rewards.entity.Transaction;

import java.util.List;

public interface TransactionService {
    List<Transaction> findByCustomerId(long customerId);

}
