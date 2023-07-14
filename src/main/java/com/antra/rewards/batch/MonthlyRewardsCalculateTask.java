package com.antra.rewards.batch;

import com.antra.rewards.entity.Customer;
import com.antra.rewards.entity.Rewards;
import com.antra.rewards.entity.Transaction;
import com.antra.rewards.repository.CustomerRepository;
import com.antra.rewards.repository.RewardsRepository;
import com.antra.rewards.repository.TransactionRepository;
import com.antra.rewards.utils.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * A batch task to calculate the reward points earned for each customer per month and total,
 * given all the transactions of every customer during a period of time(1 year for example).
 * This batch task can only be executed once by admin user, we should implement it with
 * batch framework like Spring batch in industry project. This is a demo and can only
 * independently execute without any external dependencies like DB and APIs, so make it simple.
 */
@Component
public class MonthlyRewardsCalculateTask {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    RewardsRepository rewardsRepository;
    /**
     * customer size processed each time, in demo mode, we only have 3 customers
     * so process 1 each time in simulating the batch process. In production mode,
     * we recommend this value to be set customized(eg. 100, assume that we process
     * transaction in a year and each customer has 30 transactions monthly, so the handle
     * 100 * 30 * 12 transactions a time)
     */
    private final static int CUSTOMER_SIZE_EACH_TIME = 1;

    /**
     * batch process page by page
     */
    public void start() {
        Page<Customer> page = customerRepository.findAll(PageRequest.of(0, CUSTOMER_SIZE_EACH_TIME));
        int totalPages = page.getTotalPages();
        for (int i = 0; i < totalPages; ) {
            for (Customer customer : page.getContent()) {
                List<Transaction> transactions = transactionRepository.findByCustomerId(customer.getId(), Sort.unsorted());
                List<Rewards> rewards = genCustomerRewards(customer, transactions);
                customerRepository.save(customer);
                rewardsRepository.saveAll(rewards);
            }
            page = customerRepository.findAll(PageRequest.of(++i, CUSTOMER_SIZE_EACH_TIME));
        }
    }

    public List<Rewards> genCustomerRewards(Customer customer, List<Transaction> transactions) {
        Map<String, List<Transaction>> cusMonthTransMap = transactions.stream().collect(Collectors.groupingBy(t -> TimeUtil.getYearMonth(t.getTransactionTime())));
        List<Rewards> rewards = new ArrayList<>();
        int totalPoints = 0;
        for (String yearMonth : cusMonthTransMap.keySet()) {
            Rewards newRewards = genCustomerRewardsByMonth(customer.getId(), cusMonthTransMap.get(yearMonth));
            rewards.add(newRewards);
            totalPoints += newRewards.getPoints();
        }
        customer.setPoints(totalPoints);
        return rewards;
    }

    public Rewards genCustomerRewardsByMonth(long customerId, List<Transaction> transactions) {
        LocalDateTime transactionTime = transactions.get(0).getTransactionTime();
        int monthPoints = transactions.stream().map(Transaction::getPoints).reduce(Integer::sum).get();
        return new Rewards(customerId, monthPoints, transactionTime.getYear(), transactionTime.getMonthValue());
    }
}
