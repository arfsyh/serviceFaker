package com.faker.servicefaker.controller;

import com.faker.servicefaker.dto.NotificationDto;
import com.faker.servicefaker.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("api/notification")
public class NotificationController {
    private NotificationService notificationService;

    @GetMapping("/all")
    public ResponseEntity<List<NotificationDto>> getNotif(@RequestHeader("env") String env,@RequestHeader("msisdn") String msisdn){
        List<NotificationDto> n = notificationService.findByMSISDN(msisdn, env);
        return ResponseEntity.ok(n);
    }

    @GetMapping("/last")
    public ResponseEntity<List<NotificationDto>> getLastNotif(@RequestHeader("env") String env,@RequestHeader("msisdn") String msisdn){
        List<NotificationDto> n = notificationService.findFirstByMsisdn(msisdn, env);
        return ResponseEntity.ok(n);
    }

}
