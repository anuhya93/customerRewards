package com.example.controller;

import com.example.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Customer;
import com.example.model.Rewards;
import com.example.repository.CustomerRepository;

@RestController
@RequestMapping("/customers")
public class RewardsController {

    @Autowired
    RewardService rewardService;

    @Autowired
    CustomerRepository customerRepository;

    @GetMapping(value = "/{customerId}/rewards",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Rewards> getRewardsByCustomerId(@PathVariable("customerId") Long customerId){
        Customer customer = customerRepository.findByCustomerId(customerId);
        if(customer == null)
        {
            throw new RuntimeException(" customer Id is missing ");
        }
        Rewards customerRewards = rewardService.getRewardsByCustomerId(customerId);
        return new ResponseEntity<>(customerRewards,HttpStatus.OK);
    }

}
