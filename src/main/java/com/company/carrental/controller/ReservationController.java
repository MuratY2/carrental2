package com.company.carrental.controller;

import com.company.carrental.dto.ReservationRequestDTO;
import com.company.carrental.dto.ReservationResponseDTO;
import com.company.carrental.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
