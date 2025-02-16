package com.example.service;

import com.example.model.Rewards;

public interface RewardService {
    public Rewards getRewardsByCustomerId(Long customerId);
}

