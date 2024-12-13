package com.faker.servicefaker.service;

import com.faker.servicefaker.dto.SubscriptionDto;

import java.util.List;

public interface SubscriptionService {
    SubscriptionDto createSubs(SubscriptionDto subscriptionDto, String env);
    List<SubscriptionDto> findByMSISDN(String msisdn,String env);

    SubscriptionDto findAllByBuyDateAndMsisdn(String buyDate, String msisdn,String env);
}
