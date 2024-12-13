package com.faker.servicefaker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriberBalanceDto {
    private String msisdn;
    private String subscriberType;
    private Long balance;
}
