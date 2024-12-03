package com.company.carrental.service;

import com.company.carrental.dto.CarDTO;
import com.company.carrental.entity.Car;
import com.company.carrental.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarDTO> findAvailableCars(String carType, String transmissionType) {
        List<Car> cars = carRepository.findByStatusAndCarTypeAndTransmissionType("AVAILABLE", carType, transmissionType);
        return cars.stream()
                   .map(car -> new CarDTO(
                           car.getBrand(),
                           car.getModel(),
                           car.getCarType(),
                           car.getTransmissionType(),
                           car.getBarcode()))
                   .collect(Collectors.toList());
    }
}
