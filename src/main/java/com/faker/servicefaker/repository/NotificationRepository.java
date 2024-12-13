package com.faker.servicefaker.repository;

import com.faker.servicefaker.entity.Notification;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
    List<Notification> findBymsisdn(String msisdn, Sort sort);
    List<Notification> findFirstByMsisdn(String msisdn, Sort sort);
//    Notification findFirstByMsisdnOrderByIdIdDesc(String msisdn);
}
