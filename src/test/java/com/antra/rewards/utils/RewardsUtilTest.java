package com.antra.rewards.utils;

import org.junit.Assert;
import org.junit.Test;

public class RewardsUtilTest {

    @Test
    public void testCalculatePoints() {
        Assert.assertEquals(90, RewardsUtil.calculatePoints(12_000));
        Assert.assertEquals(50, RewardsUtil.calculatePoints(10_000));

        Assert.assertEquals(0, RewardsUtil.calculatePoints(0));
        Assert.assertEquals(0, RewardsUtil.calculatePoints(-1));
        Assert.assertEquals(0, RewardsUtil.calculatePoints(5_099));
        Assert.assertEquals(1, RewardsUtil.calculatePoints(5_101));
        Assert.assertEquals(1_716, RewardsUtil.calculatePoints(93_390));
    }
}