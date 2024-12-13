package com.faker.servicefaker.mapper;

import com.faker.servicefaker.dto.PackageDto;
import com.faker.servicefaker.entity.Package;

public class PackageMapper {
    public static PackageDto mapToPackageDto (Package p){
        return new PackageDto(
                p.getServiceId(),
                p.getPackageName(),
                p.getPackageType(),
                p.getPrice()
        );
    }

    public static Package mapToPackage (PackageDto packageDto){
        return new Package(
                packageDto.getServiceId(),
                packageDto.getPackageName(),
                packageDto.getPackageType(),
                packageDto.getPrice()
        );
    }
}
