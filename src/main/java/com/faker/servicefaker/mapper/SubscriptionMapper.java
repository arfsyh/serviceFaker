package com.faker.servicefaker.mapper;

import com.faker.servicefaker.dto.SubscriptionDto;
import com.faker.servicefaker.entity.Subscription;

public class SubscriptionMapper {
    public static SubscriptionDto mapToSubscriptionDto (Subscription subscription){
        return new SubscriptionDto(
                subscription.getId(),
                subscription.getMsisdn(),
                subscription.getServiceId(),
                subscription.getPackageName(),
                subscription.getBuyDate()
        );
    }

    public static Subscription mapToSubscription(SubscriptionDto subscriptionDto){
        return new Subscription(
                subscriptionDto.getId(),
                subscriptionDto.getMsisdn(),
                subscriptionDto.getServiceId(),
                subscriptionDto.getPackageName(),
                subscriptionDto.getBuyDate()
        );
    }
}
