package com.company.carrental.repository;

import com.company.carrental.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    Service findByCode(String code);
    List<Service> findByCodeIn(List<String> codes);
}
