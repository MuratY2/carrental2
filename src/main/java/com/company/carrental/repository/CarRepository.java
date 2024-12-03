package com.company.carrental.repository;

import com.company.carrental.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findByStatusAndCarTypeAndTransmissionType(String status, String carType, String transmissionType);
    Optional<Car> findByBarcode(String barcode);
}
