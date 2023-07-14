package com.antra.rewards.init;

import com.antra.rewards.Application;
import com.antra.rewards.batch.MonthlyRewardsCalculateTask;
import com.antra.rewards.entity.Customer;
import com.antra.rewards.entity.Transaction;
import com.antra.rewards.repository.CustomerRepository;
import com.antra.rewards.repository.TransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.Random;

@Component
public class DataInitialization {

    private static final Logger log = LoggerFactory.getLogger(Application.class);

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private MonthlyRewardsCalculateTask rewardsCalculateTask;

    @EventListener(ApplicationReadyEvent.class)
    public void initDataAfterStartup() {
        log.info("Start initDataAfterStartup...");
        Random rand = new Random();
        for (int i = 0; i < 3; i++) {
            String name = "demo" + (i + 1);
            customerRepository.save(new Customer(name, name + "@antra.com", DigestUtils.md5DigestAsHex(name.getBytes()), "antra" + i, 0));
        }
        for (int i = 0; i < 100; i++) {
            transactionRepository.save(new Transaction(1l, rand.nextInt(1000) * 100 + rand.nextInt(100), LocalDateTime.of(2022, rand.nextInt(12) + 1, rand.nextInt(27) + 1, 1, 1)));
            transactionRepository.save(new Transaction(2l, rand.nextInt(1000) * 100 + rand.nextInt(100), LocalDateTime.of(2022, rand.nextInt(12) + 1, rand.nextInt(27) + 1, 1, 1)));
            transactionRepository.save(new Transaction(3l, rand.nextInt(1000) * 100 + rand.nextInt(100), LocalDateTime.of(2022, rand.nextInt(12) + 1, rand.nextInt(27) + 1, 1, 1)));
        }
        log.info("End initDataAfterStartup...");
        // async calculate by month
        asyncCalculateByMonth();
    }

    @Async
    public void asyncCalculateByMonth() {
        log.info("Start async calculate by month...");
        rewardsCalculateTask.start();
        log.info("End async calculate by month...");
    }
}
