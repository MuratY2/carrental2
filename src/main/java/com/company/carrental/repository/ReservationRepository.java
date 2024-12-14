package com.company.carrental.repository;

import com.company.carrental.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByReservationNumber(String reservationNumber);
    Optional<Reservation> findByReservationNumber(String reservationNumber);
    List<Reservation> findByCar_StatusIn(List<String> statuses);

}
