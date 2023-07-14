package com.antra.rewards.service;

import com.antra.rewards.entity.Rewards;

import java.util.List;

public interface RewardsService {
    List<Rewards> findByCustomerId(long customerId);

}
