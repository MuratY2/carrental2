package com.company.carrental.repository;

import com.company.carrental.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Location findByCode(String code);
}
