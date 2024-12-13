package com.faker.servicefaker.service.impl;

import com.faker.servicefaker.config.DataSourceContextHolder;
import com.faker.servicefaker.dto.PackageDto;
import com.faker.servicefaker.entity.Package;
import com.faker.servicefaker.exception.ResourceNotFoundException;
import com.faker.servicefaker.mapper.PackageMapper;
import com.faker.servicefaker.repository.PackageRepository;
import com.faker.servicefaker.service.PackageService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class PackageServiceImpl implements PackageService {
    private PackageRepository packageRepository;
    @Override
    public PackageDto createPackage(PackageDto packageDto, String env) {
        DataSourceContextHolder.setDataSourceType(env);
        Package p = PackageMapper.mapToPackage(packageDto);
        Package savedPackage = packageRepository.save(p);
        DataSourceContextHolder.clearDataSourceType();
        return PackageMapper.mapToPackageDto(savedPackage);
    }

    @Override
    public PackageDto findById(String serviceId, String env) {
        DataSourceContextHolder.setDataSourceType(env);
        Package p = packageRepository.findById(serviceId)
                .orElseThrow(() -> new ResourceNotFoundException("package with : " + serviceId + " is not exist"));
        DataSourceContextHolder.clearDataSourceType();

        return PackageMapper.mapToPackageDto(p);
    }
}
