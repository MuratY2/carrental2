package com.company.carrental.service;

import com.company.carrental.dto.RentedCarDTO;
import com.company.carrental.dto.ReservationRequestDTO;
import com.company.carrental.dto.ReservationResponseDTO;
import com.company.carrental.entity.*;
import com.company.carrental.repository.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final CarRepository carRepository;
    private final MemberRepository memberRepository;
    private final LocationRepository locationRepository;
    private final EquipmentRepository equipmentRepository;
    private final ReservationRepository reservationRepository;

    public ReservationService(CarRepository carRepository,
                              MemberRepository memberRepository,
                              LocationRepository locationRepository,
                              EquipmentRepository equipmentRepository,
                              ReservationRepository reservationRepository) {
        this.carRepository = carRepository;
        this.memberRepository = memberRepository;
        this.locationRepository = locationRepository;
        this.equipmentRepository = equipmentRepository;
        this.reservationRepository = reservationRepository;
    }

    public ReservationResponseDTO makeReservation(ReservationRequestDTO request) {
        Car car = carRepository.findByBarcode(request.getCarBarcode())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Car not found"));

        if (!"AVAILABLE".equalsIgnoreCase(car.getStatus())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Car is not available");
        }

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Member not found"));

        Location pickUpLocation = locationRepository.findByCode(request.getPickUpLocationCode());
        if (pickUpLocation == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pick-up location not found");
        }

        Location dropOffLocation = locationRepository.findByCode(request.getDropOffLocationCode());
        if (dropOffLocation == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Drop-off location not found");
        }

        List<Equipment> equipmentList = new ArrayList<>();
        if (request.getEquipmentCodes() != null && !request.getEquipmentCodes().isEmpty()) {
            equipmentList = equipmentRepository.findByCodeIn(request.getEquipmentCodes());
        }

        String reservationNumber = generateReservationNumber();

        car.setStatus("LOANED");
        carRepository.save(car);

        LocalDateTime creationDate = LocalDateTime.now();
        LocalDateTime pickUpDate = creationDate.plusDays(1);
        LocalDateTime dropOffDate = pickUpDate.plusDays(request.getDayCount());

        double totalAmount = request.getDayCount() * car.getDailyPrice();
        for (Equipment equipment : equipmentList) {
            totalAmount += equipment.getPrice();
        }

        Reservation reservation = new Reservation();
        reservation.setReservationNumber(reservationNumber);
        reservation.setCreationDate(creationDate);
        reservation.setPickUpDate(pickUpDate);
        reservation.setDropOffDate(dropOffDate);
        reservation.setStatus("ACTIVE");
        reservation.setCar(car);
        reservation.setMember(member);
        reservation.setPickUpLocation(pickUpLocation);
        reservation.setDropOffLocation(dropOffLocation);
        reservation.setEquipmentList(equipmentList);

        reservationRepository.save(reservation);

        ReservationResponseDTO responseDTO = new ReservationResponseDTO();
        responseDTO.setReservationNumber(reservationNumber);
        responseDTO.setPickUpDateTime(pickUpDate);
        responseDTO.setDropOffDateTime(dropOffDate);
        responseDTO.setPickUpLocationCode(pickUpLocation.getCode());
        responseDTO.setPickUpLocationName(pickUpLocation.getName());
        responseDTO.setDropOffLocationCode(dropOffLocation.getCode());
        responseDTO.setDropOffLocationName(dropOffLocation.getName());
        responseDTO.setTotalAmount(totalAmount);

        return responseDTO;
    }

    public List<RentedCarDTO> getAllRentedCars() {
        List<String> statuses = List.of("LOANED", "RESERVED");
        List<Reservation> reservations = reservationRepository.findByCar_StatusIn(statuses);

        if (reservations.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No rented or reserved cars found.");
        }

        return reservations.stream()
                .map(reservation -> new RentedCarDTO(
                        reservation.getCar().getBrand(),
                        reservation.getCar().getModel(),
                        reservation.getCar().getCarType(),
                        reservation.getCar().getTransmissionType(),
                        reservation.getCar().getBarcode(),
                        reservation.getReservationNumber(),
                        reservation.getMember().getName(),
                        reservation.getDropOffDate(),
                        reservation.getDropOffLocation().getName(),
                        ChronoUnit.DAYS.between(reservation.getPickUpDate(), reservation.getDropOffDate()) // Inline day count calculation
                ))
                .collect(Collectors.toList());
    }

    private String generateReservationNumber() {
        String number;
        do {
            number = String.format("%08d", new Random().nextInt(100_000_000));
        } while (reservationRepository.existsByReservationNumber(number));
        return number;
    }

    public boolean returnCar(String reservationNumber) {
        // Find the reservation
        Reservation reservation = reservationRepository.findByReservationNumber(reservationNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));
    
        // Check if the reservation status is already COMPLETED
        if ("COMPLETED".equalsIgnoreCase(reservation.getStatus())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Car has already been returned.");
        }
    
        // Update reservation and car
        reservation.setStatus("COMPLETED");
        reservation.setReturnDate(LocalDateTime.now());
        reservation.getCar().setStatus("AVAILABLE");
    
        // Save the changes
        reservationRepository.save(reservation);
        carRepository.save(reservation.getCar());
    
        return true;
    }

    public boolean cancelReservation(String reservationNumber) {
        // Find the reservation by reservation number
        Reservation reservation = reservationRepository.findByReservationNumber(reservationNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));
    
        // Check if the reservation is already cancelled
        if ("CANCELLED".equalsIgnoreCase(reservation.getStatus())) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "Reservation is already cancelled.");
        }
    
        // Update the reservation status and car status
        reservation.setStatus("CANCELLED");
        reservation.getCar().setStatus("AVAILABLE");
    
        // Save updates to the database
        reservationRepository.save(reservation);
        carRepository.save(reservation.getCar());
    
        return true;
    }

    public boolean addEquipmentToReservation(String reservationNumber, String equipmentCode) {
        // Find the reservation by reservation number
        Reservation reservation = reservationRepository.findByReservationNumber(reservationNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));
    
        // Find the equipment by its code
        Equipment equipment = equipmentRepository.findByCode(equipmentCode);
    
        // If equipment is not found, throw an exception
        if (equipment == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Equipment not found");
        }
    
        // Check if the equipment is already added
        if (reservation.getEquipmentList().contains(equipment)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Equipment already added to reservation");
        }
    
        // Add the equipment to the reservation
        reservation.getEquipmentList().add(equipment);
    
        // Save the updated reservation
        reservationRepository.save(reservation);
    
        return true;
    }

    public boolean deleteReservation(String reservationNumber) {
        // Find the reservation by its number
        Reservation reservation = reservationRepository.findByReservationNumber(reservationNumber)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found"));
    
        // Check if the reservation is already canceled or completed
        if ("CANCELLED".equalsIgnoreCase(reservation.getStatus()) || "COMPLETED".equalsIgnoreCase(reservation.getStatus())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Cannot delete a cancelled or completed reservation.");
        }
    
        // Delete the reservation
        reservationRepository.delete(reservation);
        return true;
    }
    
    
    
}
