package com.faker.servicefaker.mapper;

import com.faker.servicefaker.dto.SubscriberDto;
import com.faker.servicefaker.entity.Subscriber;

public class SubscriberMapper {
    public static SubscriberDto mapToSubscriberDto (Subscriber subscriber){
        return new SubscriberDto(
                subscriber.getMsisdn(),
                subscriber.getSubscriberType(),
                subscriber.getStatus(),
                subscriber.getBalance()
        );
    }

    public static Subscriber mapToSubscriber (SubscriberDto subscriberDto){
        return new Subscriber(
                subscriberDto.getMsisdn(),
                subscriberDto.getSubscriberType(),
                subscriberDto.getStatus(),
                subscriberDto.getBalance()
        );
    }
}
