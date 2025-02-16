package com.example.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.constants.Constants;
import com.example.entity.Transaction;
import com.example.model.Rewards;
import com.example.repository.TransactionRepository;

@Service
public class RewardServiceImpl implements RewardService {


    @Autowired
    TransactionRepository transactionRepository;

    public Rewards getRewardsByCustomerId(Long customerId) {

        Timestamp lastMonthTimestamp = getDateBasedOnOffSetDays(Constants.daysInMonths);
        Timestamp lastSecondMonthTimestamp = getDateBasedOnOffSetDays(2*Constants.daysInMonths);
        Timestamp lastThirdMonthTimestamp = getDateBasedOnOffSetDays(3*Constants.daysInMonths);

        List<Transaction> lastMonthTransactions = transactionRepository.findAllByCustomerIdAndTransactionDateBetween(
                customerId, lastMonthTimestamp, Timestamp.from(Instant.now()));
        List<Transaction> penultimateMonthTransactions = transactionRepository
                .findAllByCustomerIdAndTransactionDateBetween(customerId, lastSecondMonthTimestamp, lastMonthTimestamp);
        List<Transaction> antiPenultimateMonthTransactions = transactionRepository
                .findAllByCustomerIdAndTransactionDateBetween(customerId, lastThirdMonthTimestamp,
                        lastSecondMonthTimestamp);

        Long lastMonthRewardPoints = getRewardsPerMonth(lastMonthTransactions);
        Long penultimateMonthRewardPoints = getRewardsPerMonth(penultimateMonthTransactions);
        Long antiPenultimateMonthRewardPoints = getRewardsPerMonth(antiPenultimateMonthTransactions);

        Rewards customerRewards = new Rewards();
        customerRewards.setCustomerId(customerId);
        customerRewards.setLastMonthRewardPoints(lastMonthRewardPoints);
        customerRewards.setPenultimateMonthRewardPoints(penultimateMonthRewardPoints);
        customerRewards.setAntiPenultimateMonthRewardPoints(antiPenultimateMonthRewardPoints);
        customerRewards.setTotalRewards(lastMonthRewardPoints + penultimateMonthRewardPoints + antiPenultimateMonthRewardPoints);

        return customerRewards;

    }

    private Long getRewardsPerMonth(List<Transaction> transactions) {
        return transactions.stream().map(transaction -> calculateRewards(transaction))
                .collect(Collectors.summingLong(r -> r.longValue()));
    }

    private Long calculateRewards(Transaction t) {
        if (t.getTransactionAmount() > Constants.firstRewardLimit && t.getTransactionAmount() <= Constants.secondRewardLimit) {
            return Math.round(t.getTransactionAmount() - Constants.firstRewardLimit);
        } else if (t.getTransactionAmount() > Constants.secondRewardLimit) {
            return Math.round(t.getTransactionAmount() - Constants.secondRewardLimit) * 2
                    + (Constants.secondRewardLimit - Constants.firstRewardLimit);
        } else
            return 0L;

    }

    public Timestamp getDateBasedOnOffSetDays(int days) {
        return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
    }

}
