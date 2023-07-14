package com.antra.rewards.repository;

import com.antra.rewards.entity.Rewards;
import com.antra.rewards.entity.Transaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface RewardsRepository extends PagingAndSortingRepository<Rewards, Long> {
    List<Rewards> findByCustomerId(long customerId, Sort sort);
}
