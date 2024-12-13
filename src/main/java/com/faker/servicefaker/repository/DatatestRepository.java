package com.faker.servicefaker.repository;

import com.faker.servicefaker.entity.Datatest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatatestRepository extends JpaRepository<Datatest, Long> {
}
