package com.faker.servicefaker.service.impl;

import com.faker.servicefaker.config.DataSourceContextHolder;
import com.faker.servicefaker.dto.SubscriberBalanceDto;
import com.faker.servicefaker.dto.SubscriberDto;
import com.faker.servicefaker.dto.SubscriberStatusDto;
import com.faker.servicefaker.entity.Subscriber;
import com.faker.servicefaker.exception.ResourceNotFoundException;
import com.faker.servicefaker.mapper.SubscriberMapper;
import com.faker.servicefaker.repository.SubscriberRepository;
import com.faker.servicefaker.service.SubscriberService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SubscriberServiceImpl implements SubscriberService {
    private SubscriberRepository subscriberRepository;
    @Override
    public SubscriberDto createSubscriber(SubscriberDto subscriberDto,String env) {
        DataSourceContextHolder.setDataSourceType(env);
        Subscriber subscriber = SubscriberMapper.mapToSubscriber(subscriberDto);
        Subscriber savedSubscriber = subscriberRepository.save(subscriber);
        DataSourceContextHolder.clearDataSourceType();
        return SubscriberMapper.mapToSubscriberDto(savedSubscriber);
    }

    @Override
    public SubscriberDto findById(String msisdn,String env) {
        DataSourceContextHolder.setDataSourceType(env);
        Subscriber subscriber = subscriberRepository.findById(msisdn)
                .orElseThrow(() -> new ResourceNotFoundException("MSISDN : " + msisdn + " is not exist"));
        DataSourceContextHolder.clearDataSourceType();
        return SubscriberMapper.mapToSubscriberDto(subscriber);
    }

    @Override
    public SubscriberDto updateStatus(String msisdn, SubscriberStatusDto subscriberStatusDto,String env) {
        DataSourceContextHolder.setDataSourceType(env);
        Subscriber subscriber = subscriberRepository.findById(msisdn)
                .orElseThrow(() -> new ResourceNotFoundException("MSISDN : " + msisdn + " is not exist"));
        subscriber.setStatus(subscriberStatusDto.getStatus());
        Subscriber savedSubscriber = subscriberRepository.save(subscriber);
        DataSourceContextHolder.clearDataSourceType();
        return SubscriberMapper.mapToSubscriberDto(savedSubscriber);
    }

    @Override
    public SubscriberDto updateBalance(String msisdn, SubscriberBalanceDto subscriberBalanceDto, String env) {
        DataSourceContextHolder.setDataSourceType(env);
        Subscriber subscriber = subscriberRepository.findById(msisdn)
                .orElseThrow(() -> new ResourceNotFoundException("MSISDN : " + msisdn + " is not exist"));
        subscriber.setBalance(subscriberBalanceDto.getBalance());
        Subscriber savedSubscriber = subscriberRepository.save(subscriber);
        DataSourceContextHolder.clearDataSourceType();
        return SubscriberMapper.mapToSubscriberDto(savedSubscriber);
    }

}
