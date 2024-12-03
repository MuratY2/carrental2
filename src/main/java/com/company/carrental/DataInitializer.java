package com.company.carrental;

import com.company.carrental.entity.*;
import com.company.carrental.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

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
        memberRepository.saveAll(Arrays.asList(member1, member2));

        // Insert locations
        Location location1 = new Location("LOC1", "Downtown", "123 Main St");
        Location location2 = new Location("LOC2", "Airport", "456 Airport Rd");
        locationRepository.saveAll(Arrays.asList(location1, location2));

        // Insert cars
        Car car1 = new Car("CAR001", "ABC123", "Toyota", "Corolla", "Automatic", "Standard", 5, 50.0, "AVAILABLE");
        Car car2 = new Car("CAR002", "XYZ456", "Honda", "Civic", "Manual", "Standard", 5, 45.0, "AVAILABLE");
        carRepository.saveAll(Arrays.asList(car1, car2));

        // Insert equipment
        Equipment equipment1 = new Equipment("GPS", "GPS Navigation", 10.0);
        Equipment equipment2 = new Equipment("BABY_SEAT", "Baby Seat", 15.0);
        equipmentRepository.saveAll(Arrays.asList(equipment1, equipment2));

        System.out.println("Sample data initialized successfully!");
    }
}
