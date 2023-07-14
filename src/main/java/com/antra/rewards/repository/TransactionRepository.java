package com.antra.rewards.repository;

import com.antra.rewards.entity.Transaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TransactionRepository extends PagingAndSortingRepository<Transaction, Long> {
    List<Transaction> findByCustomerId(long customerId, Sort sort);
}
