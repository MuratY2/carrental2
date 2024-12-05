package com.company.carrental;

import com.company.carrental.entity.*;
import com.company.carrental.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime; // Import for LocalDateTime
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    private final MemberRepository memberRepository;
    private final LocationRepository locationRepository;
    private final CarRepository carRepository;
    private final EquipmentRepository equipmentRepository;
    private final ReservationRepository reservationRepository;

    public DataInitializer(MemberRepository memberRepository, LocationRepository locationRepository,
                           CarRepository carRepository, EquipmentRepository equipmentRepository,
                           ReservationRepository reservationRepository) {
        this.memberRepository = memberRepository;
        this.locationRepository = locationRepository;
        this.carRepository = carRepository;
        this.equipmentRepository = equipmentRepository;
        this.reservationRepository = reservationRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Insert members
        Member member1 = new Member("John Doe", "123 Elm Street", "john.doe@example.com", "1234567890", "D12345");
        Member member2 = new Member("Jane Smith", "456 Oak Avenue", "jane.smith@example.com", "9876543210", "D67890");
        Member member3 = new Member("Alice Brown", "789 Pine Drive", "alice.brown@example.com", "1112223334", "D12346");
        Member member4 = new Member("Bob White", "321 Cedar Blvd", "bob.white@example.com", "4445556667", "D12347");
        Member member5 = new Member("Charlie Black", "654 Birch Ln", "charlie.black@example.com", "7778889990", "D12348");
        memberRepository.saveAll(Arrays.asList(member1, member2, member3, member4, member5));

        // Insert locations
        Location location1 = new Location("LOC1", "Downtown", "123 Main St");
        Location location2 = new Location("LOC2", "Airport", "456 Airport Rd");
        Location location3 = new Location("LOC3", "City Center", "789 Center Ave");
        Location location4 = new Location("LOC4", "Suburb", "101 Suburb St");
        Location location5 = new Location("LOC5", "Train Station", "202 Train Ln");
        locationRepository.saveAll(Arrays.asList(location1, location2, location3, location4, location5));

        // Insert cars
        Car car1 = new Car("CAR001", "ABC123", "Toyota", "Corolla", "Automatic", "Standard", 5, 50.0, "AVAILABLE");
        Car car2 = new Car("CAR002", "XYZ456", "Honda", "Civic", "Manual", "Standard", 5, 45.0, "AVAILABLE");
        Car car3 = new Car("CAR003", "DEF789", "BMW", "X5", "Automatic", "SUV", 7, 100.0, "AVAILABLE");
        Car car4 = new Car("CAR004", "GHI012", "Audi", "A4", "Automatic", "Luxury", 5, 120.0, "AVAILABLE");
        Car car5 = new Car("CAR005", "JKL345", "Ford", "Focus", "Manual", "Economy", 5, 30.0, "AVAILABLE");
        carRepository.saveAll(Arrays.asList(car1, car2, car3, car4, car5));

        // Insert equipment
        Equipment equipment1 = new Equipment("GPS", "GPS Navigation", 10.0);
        Equipment equipment2 = new Equipment("BABY_SEAT", "Baby Seat", 15.0);
        Equipment equipment3 = new Equipment("SNOW_TIRES", "Snow Tires", 20.0);
        Equipment equipment4 = new Equipment("WIFI", "In-Car WiFi", 5.0);
        Equipment equipment5 = new Equipment("ROOF_BOX", "Roof Box", 25.0);
        equipmentRepository.saveAll(Arrays.asList(equipment1, equipment2, equipment3, equipment4, equipment5));

        // Insert reservations
        Reservation reservation1 = new Reservation("R000001", LocalDateTime.now(), LocalDateTime.now().plusDays(1), LocalDateTime.now().plusDays(5), "ACTIVE", car1, member1, location1, location2);
        Reservation reservation2 = new Reservation("R000002", LocalDateTime.now(), LocalDateTime.now().plusDays(2), LocalDateTime.now().plusDays(6), "ACTIVE", car2, member2, location3, location4);
        Reservation reservation3 = new Reservation("R000003", LocalDateTime.now(), LocalDateTime.now().plusDays(3), LocalDateTime.now().plusDays(7), "PENDING", car3, member3, location4, location5);
        Reservation reservation4 = new Reservation("R000004", LocalDateTime.now(), LocalDateTime.now().plusDays(4), LocalDateTime.now().plusDays(8), "CONFIRMED", car4, member4, location2, location1);
        Reservation reservation5 = new Reservation("R000005", LocalDateTime.now(), LocalDateTime.now().plusDays(5), LocalDateTime.now().plusDays(9), "CANCELLED", car5, member5, location5, location3);
        reservationRepository.saveAll(Arrays.asList(reservation1, reservation2, reservation3, reservation4, reservation5));

        System.out.println("Sample data initialized successfully!");
    }
}
