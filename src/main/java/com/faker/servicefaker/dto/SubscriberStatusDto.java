package com.faker.servicefaker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SubscriberStatusDto {
    private String msisdn;
    private String subscriberType;
    private String status;
}
