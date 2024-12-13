package com.faker.servicefaker.service;

import com.faker.servicefaker.dto.NotificationDto;

import java.util.List;

public interface NotificationService {
    NotificationDto createNotification(NotificationDto notificationDto,String env);
    List<NotificationDto> findByMSISDN(String msisdn,String env);
    List<NotificationDto> findFirstByMsisdn(String msisdn,String env);

//    NotificationDto findFirstBymsisdnOrderByidDESC(String msisdn);
}
