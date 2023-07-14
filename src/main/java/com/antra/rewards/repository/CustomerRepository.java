package com.antra.rewards.repository;

import com.antra.rewards.entity.Customer;
import com.antra.rewards.entity.Rewards;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    Customer findByLoginName(String loginName);
}
