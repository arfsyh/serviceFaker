package com.faker.servicefaker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscriber")
public class Subscriber {
    @Id
    private String msisdn;
    @Column(name = "subscriber_type")
    private String subscriberType;
    private String status;
    private Long balance;
}
