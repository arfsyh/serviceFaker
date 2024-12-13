package com.faker.servicefaker.service.impl;

import com.faker.servicefaker.config.DataSourceContextHolder;
import com.faker.servicefaker.dto.SubscriptionDto;
import com.faker.servicefaker.entity.Subscription;
import com.faker.servicefaker.exception.ResourceNotFoundException;
import com.faker.servicefaker.mapper.SubscriptionMapper;
import com.faker.servicefaker.repository.SubscriptionRepository;
import com.faker.servicefaker.service.SubscriptionService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubscriptionServiceImpl implements SubscriptionService {
    private SubscriptionRepository subscriptionRepository;
    @Override
    @Transactional(rollbackFor = Exception.class)
    public SubscriptionDto createSubs(SubscriptionDto subscriptionDto, String env) {
        DataSourceContextHolder.setDataSourceType(env);
        Subscription subscription = SubscriptionMapper.mapToSubscription(subscriptionDto);
        Subscription savedSubs = subscriptionRepository.save(subscription);
        DataSourceContextHolder.clearDataSourceType();
        return SubscriptionMapper.mapToSubscriptionDto(savedSubs);
    }

    @Override
    public List<SubscriptionDto> findByMSISDN(String msisdn, String env) {
        DataSourceContextHolder.setDataSourceType(env);
        List<Subscription> sub = subscriptionRepository.findBymsisdn(msisdn, Sort.by(Sort.Direction.DESC,"id"));
        DataSourceContextHolder.clearDataSourceType();
        return sub.stream().map(SubscriptionMapper::mapToSubscriptionDto).collect(Collectors.toList());
    }

    @Override
    public SubscriptionDto findAllByBuyDateAndMsisdn(String buyDate, String msisdn, String env) {
        DataSourceContextHolder.setDataSourceType(env);
        Subscription subscription = subscriptionRepository.findAllByBuyDateAndMsisdn(buyDate,msisdn);
        if (subscription == null) {
            throw new ResourceNotFoundException("no data");
        }
        DataSourceContextHolder.clearDataSourceType();
        return SubscriptionMapper.mapToSubscriptionDto(subscription);
    }

}
