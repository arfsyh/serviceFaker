package com.faker.servicefaker.service;

import com.faker.servicefaker.dto.SubscriberBalanceDto;
import com.faker.servicefaker.dto.SubscriberDto;
import com.faker.servicefaker.dto.SubscriberStatusDto;

public interface SubscriberService {
    SubscriberDto createSubscriber(SubscriberDto subscriberDto,String env);
    SubscriberDto findById(String msisdn,String env);

    SubscriberDto updateStatus(String msisdn, SubscriberStatusDto subscriberStatusDto, String env);
    SubscriberDto updateBalance(String msisdn, SubscriberBalanceDto subscriberBalanceDto, String env);
}
