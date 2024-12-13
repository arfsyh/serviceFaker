package com.faker.servicefaker.controller;

import com.faker.servicefaker.dto.SubscriberBalanceDto;
import com.faker.servicefaker.dto.SubscriberDto;
import com.faker.servicefaker.dto.SubscriberStatusDto;
import com.faker.servicefaker.service.SubscriberService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/subscriber")
public class SubscriberController {
    private SubscriberService subscriberService;

    @PostMapping
    public ResponseEntity<SubscriberDto> createSubscriber(@RequestHeader("env") String env,@RequestBody SubscriberDto subscriberDto){
        SubscriberDto savedSubscriber = subscriberService.createSubscriber(subscriberDto, env);
        return new ResponseEntity<>(savedSubscriber, HttpStatus.CREATED);
    }

    @GetMapping("/info")
    public  ResponseEntity<SubscriberDto> getSubscriberInfo(@RequestHeader("msisdn") String msisdn,@RequestHeader("env") String env){
        SubscriberDto subscriberDto = subscriberService.findById(msisdn,env);
        return ResponseEntity.ok(subscriberDto);
    }

    @GetMapping("/status")
    public ResponseEntity<SubscriberStatusDto> getSubscriberStatus(@RequestHeader("msisdn") String msisdn,@RequestHeader("env") String env){
        SubscriberDto subscriber = subscriberService.findById(msisdn,env);
        SubscriberStatusDto status = new SubscriberStatusDto();
        status.setMsisdn(subscriber.getMsisdn());
        status.setSubscriberType(subscriber.getSubscriberType());
        status.setStatus(subscriber.getStatus());

        return ResponseEntity.ok(status);
    }

    @GetMapping("/balance")
    public ResponseEntity<SubscriberBalanceDto> getSubscriberBalance(@RequestHeader("msisdn") String msisdn,@RequestHeader("env") String env){
        SubscriberDto subscriber = subscriberService.findById(msisdn,env);
        SubscriberBalanceDto status = new SubscriberBalanceDto();
        status.setMsisdn(subscriber.getMsisdn());
        status.setSubscriberType(subscriber.getSubscriberType());
        status.setBalance(subscriber.getBalance());

        return ResponseEntity.ok(status);
    }

    @PutMapping("/status")
    public ResponseEntity<SubscriberStatusDto> updateSubscriberStatus(@RequestHeader("env") String env,@RequestBody SubscriberStatusDto subscriberStatusDto){
        SubscriberDto subscriber = subscriberService.updateStatus(subscriberStatusDto.getMsisdn(),subscriberStatusDto,env);
        SubscriberStatusDto status = new SubscriberStatusDto();
        status.setMsisdn(subscriber.getMsisdn());
        status.setStatus(subscriber.getStatus());

        return ResponseEntity.ok(status);
    }

    @PutMapping("/balance")
    public ResponseEntity<SubscriberBalanceDto> updateSubscriberBalance(@RequestHeader("env") String env,@RequestBody SubscriberBalanceDto subscriberBalanceDto){
        SubscriberDto subscriber = subscriberService.updateBalance(subscriberBalanceDto.getMsisdn(),subscriberBalanceDto, env);
        SubscriberBalanceDto status = new SubscriberBalanceDto();
        status.setMsisdn(subscriber.getMsisdn());
        status.setBalance(subscriber.getBalance());

        return ResponseEntity.ok(status);
    }
}
