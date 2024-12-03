package com.company.carrental.controller;

import com.company.carrental.dto.CarDTO;
import com.company.carrental.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/available")
    public ResponseEntity<List<CarDTO>> getAvailableCars(
            @RequestParam(required = false, defaultValue = "") String carType,
            @RequestParam(required = false, defaultValue = "") String transmissionType) {
        List<CarDTO> availableCars = carService.findAvailableCars(carType, transmissionType);
        if (availableCars.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(availableCars, HttpStatus.OK);
    }
}
