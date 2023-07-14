package com.antra.rewards.utils;

public class RewardsUtil {

    /**
     * A customer receives 2 points for every dollar spent over $100 in each transaction,
     * plus 1 point for every dollar spent over $50 in each transaction
     * (e.g. if one customer spends $120 total in a transaction, then points earned is 2x$20 + 1x$50 = 90 points).
     *
     * @param transactionAmount transactionAmount store in cents as unit
     * @return
     */
    public static int calculatePoints(int transactionAmount) {
        int rewardPoints = 0;
        if (transactionAmount < 5_100) {
            return 0;
        }
        transactionAmount /= 100;
        if (transactionAmount > 100) {
            rewardPoints = (2 * (transactionAmount - 100) + 50);
        } else if (transactionAmount > 50) {
            rewardPoints = transactionAmount - 50;
        }
        return rewardPoints;
    }
}
