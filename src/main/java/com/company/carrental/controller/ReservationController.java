package com.company.carrental.controller;

import com.company.carrental.dto.RentedCarDTO;
import com.company.carrental.dto.ReservationRequestDTO;
import com.company.carrental.dto.ReservationResponseDTO;
import com.company.carrental.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    // Constructor injection of ReservationService
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @PostMapping("/make")
    public ResponseEntity<ReservationResponseDTO> makeReservation(@RequestBody ReservationRequestDTO request) {
        ReservationResponseDTO response = reservationService.makeReservation(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rented")
    public ResponseEntity<List<RentedCarDTO>> getAllRentedCars() {
        List<RentedCarDTO> rentedCars = reservationService.getAllRentedCars();
        return ResponseEntity.ok(rentedCars);
    }

    @PostMapping("/return")
    public ResponseEntity<Boolean> returnCar(@RequestBody Map<String, String> requestBody) {
        String reservationNumber = requestBody.get("reservationNumber");
        boolean result = reservationService.returnCar(reservationNumber);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/cancel")
    public ResponseEntity<Boolean> cancelReservation(@RequestBody Map<String, String> requestBody) {
        String reservationNumber = requestBody.get("reservationNumber");
        boolean result = reservationService.cancelReservation(reservationNumber);
        return ResponseEntity.ok(result);
    }   

    @PostMapping("/add-equipment")
    public ResponseEntity<Boolean> addEquipmentToReservation(@RequestBody Map<String, String> requestBody) {
        String reservationNumber = requestBody.get("reservationNumber");
        String equipmentCode = requestBody.get("equipmentCode");
        boolean result = reservationService.addEquipmentToReservation(reservationNumber, equipmentCode);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/delete")
    public ResponseEntity<Boolean> deleteReservation(@RequestBody Map<String, String> requestBody) {
        String reservationNumber = requestBody.get("reservationNumber");
        boolean result = reservationService.deleteReservation(reservationNumber);
        return ResponseEntity.ok(result);
    }

    


}
