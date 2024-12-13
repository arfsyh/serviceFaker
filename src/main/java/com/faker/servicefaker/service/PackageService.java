package com.faker.servicefaker.service;

import com.faker.servicefaker.dto.PackageDto;

public interface PackageService {
    PackageDto createPackage(PackageDto packageDto,String env);
    PackageDto findById(String serviceId,String env);
}
