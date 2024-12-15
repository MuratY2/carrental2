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
            cars = carRepository.findByStatus("AVAILABLE");
        } else if (carType == null || carType.isEmpty()) {
            cars = carRepository.findByStatusAndTransmissionType("AVAILABLE", transmissionType);
        } else if (transmissionType == null || transmissionType.isEmpty()) {
            cars = carRepository.findByStatusAndCarType("AVAILABLE", carType);
        } else {
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
            var car = carRepository.findByBarcode(barcode)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));

            if ("LOANED".equalsIgnoreCase(car.getStatus()) || "RESERVED".equalsIgnoreCase(car.getStatus())) {
                throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Cannot delete a car that is currently rented or reserved.");
            }

            carRepository.delete(car);
            return true;
        }
}