package com.antra.rewards.service.impl;

import com.antra.rewards.entity.Transaction;
import com.antra.rewards.repository.TransactionRepository;
import com.antra.rewards.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Override
    public List<Transaction> findByCustomerId(long customerId) {
        return repository.findByCustomerId(customerId, Sort.by("transactionTime"));
    }
}
