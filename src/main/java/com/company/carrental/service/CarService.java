package com.company.carrental.service;

import com.company.carrental.dto.CarDTO;
import com.company.carrental.entity.Car;
import com.company.carrental.repository.CarRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<CarDTO> findAvailableCars(String carType, String transmissionType) {
        List<Car> cars;

        if ((carType == null || carType.isEmpty()) && (transmissionType == null || transmissionType.isEmpty())) {
            // No filters provided, return all available cars
            cars = carRepository.findByStatus("AVAILABLE");
        } else if (carType == null || carType.isEmpty()) {
            // Filter by transmission type only
            cars = carRepository.findByStatusAndTransmissionType("AVAILABLE", transmissionType);
        } else if (transmissionType == null || transmissionType.isEmpty()) {
            // Filter by car type only
            cars = carRepository.findByStatusAndCarType("AVAILABLE", carType);
        } else {
            // Filter by both car type and transmission type
            cars = carRepository.findByStatusAndCarTypeAndTransmissionType("AVAILABLE", carType, transmissionType);
        }

        return cars.stream()
                .map(car -> new CarDTO(
                        car.getBrand(),
                        car.getModel(),
                        car.getCarType(),
                        car.getTransmissionType(),
                        car.getBarcode()))
                .collect(Collectors.toList());
    }

        public boolean deleteCar(String barcode) {
            // Find the car by barcode
            var car = carRepository.findByBarcode(barcode)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));

            // Check if the car is LOANED or RESERVED
            if ("LOANED".equalsIgnoreCase(car.getStatus()) || "RESERVED".equalsIgnoreCase(car.getStatus())) {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Cannot delete a car that is currently rented or reserved.");
            }

            // Delete the car
            carRepository.delete(car);
            return true;
        }
}