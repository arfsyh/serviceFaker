package com.faker.servicefaker.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "package")
public class Package {
    @Id
    @Column(name = "service_id")
    private String serviceId;
    @Column(name = "package_name")
    private String packageName;
    @Column(name = "package_type")
    private String packageType;
    private Long price;

}
