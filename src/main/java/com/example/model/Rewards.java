package com.example.model;

public class Rewards {
    private long customerId;
    private long lastMonthRewardPoints;
    private long penultimateMonthRewardPoints;
    private long antiPenultimateMonthRewardPoints;
    private long totalRewards;

    public long getCustomerId() {
        return customerId;
    }

    public long getLastMonthRewardPoints() {
        return lastMonthRewardPoints;
    }

    public void setLastMonthRewardPoints(long lastMonthRewardPoints) {
        this.lastMonthRewardPoints = lastMonthRewardPoints;
    }

    public long penultimateMonthRewardPoints() {
        return penultimateMonthRewardPoints;
    }

    public void setPenultimateMonthRewardPoints(long penultimateMonthRewardPoints) {
        this.penultimateMonthRewardPoints = penultimateMonthRewardPoints;
    }

    public long getAntiPenultimateMonthRewardPoints() {
        return antiPenultimateMonthRewardPoints;
    }

    public void setAntiPenultimateMonthRewardPoints(long antiPenultimateMonthRewardPoints) {
        this.antiPenultimateMonthRewardPoints = antiPenultimateMonthRewardPoints;
    }

    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }



    public long getTotalRewards() {
        return totalRewards;
    }

    public void setTotalRewards(long totalRewards) {
        this.totalRewards = totalRewards;
    }
}
