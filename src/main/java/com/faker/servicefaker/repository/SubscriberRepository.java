package com.faker.servicefaker.repository;

import com.faker.servicefaker.entity.Subscriber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubscriberRepository extends JpaRepository<Subscriber,String> {
}
