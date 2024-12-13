package com.faker.servicefaker.repository;

import com.faker.servicefaker.entity.Subscription;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription,Long> {
    List<Subscription> findBymsisdn(String msisdn, Sort sort);
    Subscription findAllByBuyDateAndMsisdn(String buyDate, String msisdn);
}
