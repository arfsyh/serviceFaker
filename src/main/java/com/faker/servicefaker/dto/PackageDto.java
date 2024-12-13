package com.faker.servicefaker.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PackageDto {
    private String serviceId;
    private String packageName;
    private String packageType;
    private Long price;
}
