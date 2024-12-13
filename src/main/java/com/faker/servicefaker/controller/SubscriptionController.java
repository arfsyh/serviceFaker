package com.faker.servicefaker.controller;

import com.faker.servicefaker.dto.SubscriptionDto;
import com.faker.servicefaker.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/subscription")
public class SubscriptionController {
    private SubscriptionService subscriptionService;

    @GetMapping
    public ResponseEntity<List<SubscriptionDto>> getSubscription(@RequestHeader("env") String env,@RequestHeader("msisdn") String msisdn){
        List<SubscriptionDto> s = subscriptionService.findByMSISDN(msisdn, env);
        return ResponseEntity.ok(s);
    }

    @GetMapping("/single")
    public ResponseEntity<SubscriptionDto> getSingleValue(@RequestHeader("env") String env,@RequestHeader("buyDate") String buyDate,@RequestHeader("msisdn") String msisdn){
        SubscriptionDto s = subscriptionService.findAllByBuyDateAndMsisdn(buyDate,msisdn, env);
        return ResponseEntity.ok(s);
    }
}
