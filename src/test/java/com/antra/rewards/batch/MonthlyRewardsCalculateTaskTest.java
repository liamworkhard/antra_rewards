package com.antra.rewards.batch;

import com.antra.rewards.entity.Customer;
import com.antra.rewards.entity.Rewards;
import com.antra.rewards.entity.Transaction;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class MonthlyRewardsCalculateTaskTest {

    @Test
    public void testGenCustomerRewards() {
        MonthlyRewardsCalculateTask task = new MonthlyRewardsCalculateTask();

        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction622 = new Transaction(1l, 38634, LocalDateTime.of(2022, 1, 1, 1, 1));
        Transaction transaction1278 = new Transaction(1l, 71477, LocalDateTime.of(2022, 2, 1, 1, 1));
        Transaction transaction860 = new Transaction(1l, 50516, LocalDateTime.of(2022, 2, 1, 1, 1));
        Transaction transaction0 = new Transaction(1l, 3621, LocalDateTime.of(2022, 3, 1, 1, 1));
        Transaction transaction1010 = new Transaction(1l, 58080, LocalDateTime.of(2022, 3, 1, 1, 1));

        Assert.assertEquals(622, transaction622.getPoints());
        Assert.assertEquals(1278, transaction1278.getPoints());
        Assert.assertEquals(860, transaction860.getPoints());
        Assert.assertEquals(0, transaction0.getPoints());
        Assert.assertEquals(1010, transaction1010.getPoints());

        transactions.add(transaction622);
        transactions.add(transaction1278);
        transactions.add(transaction860);
        transactions.add(transaction0);
        transactions.add(transaction1010);

        Customer customer = new Customer();
        customer.setId(1l);
        List<Rewards> rewards = task.genCustomerRewards(customer, transactions);

        Assert.assertEquals(3, rewards.size());
        Assert.assertEquals(Optional.of(622), rewards.stream().filter(r -> r.getMonth() == 1).map(Rewards::getPoints).reduce(Integer::sum));
        Assert.assertEquals(Optional.of(1278 + 860), rewards.stream().filter(r -> r.getMonth() == 2).map(Rewards::getPoints).reduce(Integer::sum));
        Assert.assertEquals(Optional.of(0 + 1010), rewards.stream().filter(r -> r.getMonth() == 3).map(Rewards::getPoints).reduce(Integer::sum));
        Assert.assertEquals(622 + 1278 + 860 + 0 + 1010, customer.getPoints());
    }

    @Test
    public void testGenCustomerRewardsByMonth() {

        MonthlyRewardsCalculateTask task = new MonthlyRewardsCalculateTask();
        List<Transaction> transactions = new ArrayList<>();
        Transaction transaction622 = new Transaction(1l, 38634, LocalDateTime.of(2022, 7, 1, 1, 1));
        Transaction transaction1278 = new Transaction(1l, 71477, LocalDateTime.of(2022, 7, 1, 1, 1));
        Transaction transaction860 = new Transaction(1l, 50516, LocalDateTime.of(2022, 7, 1, 1, 1));
        Transaction transaction0 = new Transaction(1l, 3621, LocalDateTime.of(2022, 7, 1, 1, 1));
        Transaction transaction1010 = new Transaction(1l, 58080, LocalDateTime.of(2022, 7, 1, 1, 1));

        Assert.assertEquals(622, transaction622.getPoints());
        Assert.assertEquals(1278, transaction1278.getPoints());
        Assert.assertEquals(860, transaction860.getPoints());
        Assert.assertEquals(0, transaction0.getPoints());
        Assert.assertEquals(1010, transaction1010.getPoints());

        transactions.add(transaction622);
        transactions.add(transaction1278);
        transactions.add(transaction860);
        transactions.add(transaction0);
        transactions.add(transaction1010);

        Rewards rewards = task.genCustomerRewardsByMonth(1l, transactions);

        Assert.assertEquals(622 + 1278 + 860 + 1010, rewards.getPoints());
        Assert.assertEquals(7, rewards.getMonth());
        Assert.assertEquals(2022, rewards.getYear());
        Assert.assertEquals(1, rewards.getCustomerId());
    }
}