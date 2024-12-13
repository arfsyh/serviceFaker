package com.faker.servicefaker.service.impl;

import com.faker.servicefaker.config.DataSourceContextHolder;
import com.faker.servicefaker.dto.NotificationDto;
import com.faker.servicefaker.entity.Notification;
import com.faker.servicefaker.mapper.NotificationMapper;
import com.faker.servicefaker.repository.NotificationRepository;
import com.faker.servicefaker.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class NotificationServiceImpl implements NotificationService {
    private NotificationRepository notificationRepository;
    @Override
    public NotificationDto createNotification(NotificationDto notificationDto, String env) {
        DataSourceContextHolder.setDataSourceType(env);
        Notification notification = NotificationMapper.mapToNotification(notificationDto);
        Notification savedNotification =  notificationRepository.save(notification);
        DataSourceContextHolder.clearDataSourceType();
        return NotificationMapper.mapToNotificationDto(savedNotification);
    }

    @Override
    public List<NotificationDto> findByMSISDN(String msisdn, String env) {
        DataSourceContextHolder.setDataSourceType(env);
        List<Notification> notifications = notificationRepository.findBymsisdn(msisdn, Sort.by(Sort.Direction.DESC,"id"));
        DataSourceContextHolder.clearDataSourceType();
        return notifications.stream().map(NotificationMapper::mapToNotificationDto).collect(Collectors.toList());
    }

    @Override
    public List<NotificationDto> findFirstByMsisdn(String msisdn, String env) {
        DataSourceContextHolder.setDataSourceType(env);
        List<Notification> notifications = notificationRepository.findFirstByMsisdn(msisdn, Sort.by(Sort.Direction.DESC,"id"));
        DataSourceContextHolder.clearDataSourceType();
        return notifications.stream().map(NotificationMapper::mapToNotificationDto).collect(Collectors.toList());

    }

//    @Override
//    public NotificationDto findFirstBymsisdnOrderByidDESC(String msisdn) {
//        Notification n = notificationRepository.findFirstByMsisdnOrderByIdIdDesc(msisdn);
//        return NotificationMapper.mapToNotificationDto(n);
//    }
}
