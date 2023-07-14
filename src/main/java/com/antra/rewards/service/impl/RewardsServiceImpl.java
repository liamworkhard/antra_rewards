package com.antra.rewards.service.impl;

import com.antra.rewards.entity.Rewards;
import com.antra.rewards.repository.RewardsRepository;
import com.antra.rewards.service.RewardsService;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RewardsServiceImpl implements RewardsService {

    @Autowired
    private RewardsRepository rewardsRepository;

    @Override
    public List<Rewards> findByCustomerId(long customerId) {
        System.out.println(SecurityUtils.getSubject().getPrincipal().toString());
        return rewardsRepository.findByCustomerId(customerId, Sort.by("year", "month"));
    }
}
