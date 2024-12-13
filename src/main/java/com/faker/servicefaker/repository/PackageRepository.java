package com.faker.servicefaker.repository;

import com.faker.servicefaker.entity.Package;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackageRepository extends JpaRepository<Package,String> {
}
