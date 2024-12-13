package com.faker.servicefaker.mapper;

import com.faker.servicefaker.dto.NotificationDto;
import com.faker.servicefaker.entity.Notification;

public class NotificationMapper {
    public static NotificationDto mapToNotificationDto (Notification notification){
        return new NotificationDto(
                notification.getId(),
                notification.getMsisdn(),
                notification.getStatus(),
                notification.getMessage()
        );
    }

    public static Notification mapToNotification (NotificationDto notificationDto){
        return new Notification(
                notificationDto.getId(),
                notificationDto.getMsisdn(),
                notificationDto.getStatus(),
                notificationDto.getMessage()
        );
    }
}
