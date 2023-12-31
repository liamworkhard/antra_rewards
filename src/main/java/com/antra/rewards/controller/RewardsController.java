package com.antra.rewards.controller;

import com.antra.rewards.cache.CustomerThreadLocal;
import com.antra.rewards.entity.Rewards;
import com.antra.rewards.service.RewardsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/rewards")
public class RewardsController {
    Logger logger = LoggerFactory.getLogger(RewardsController.class);

    @Autowired
    private RewardsService rewardsService;

    @GetMapping
    public ResponseEntity<List<Rewards>> getByCustomerId() {
        logger.info("In RewardsController::getByCustomerId method start");

        List<Rewards> transactions = rewardsService.findByCustomerId(CustomerThreadLocal.get().getId());
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
