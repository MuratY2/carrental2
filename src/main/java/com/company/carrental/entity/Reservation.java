package com.company.carrental.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "reservations")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reservationNumber;
    private LocalDateTime creationDate;
    private LocalDateTime pickUpDate;
    private LocalDateTime dropOffDate;
    private LocalDateTime returnDate;
    private String status;

    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "pick_up_location_id")
    private Location pickUpLocation;

    @ManyToOne
    @JoinColumn(name = "drop_off_location_id")
    private Location dropOffLocation;

    @ManyToMany
    @JoinTable(
            name = "reservation_equipment",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "equipment_id")
    )
    private List<Equipment> equipmentList;

    @ManyToMany
    @JoinTable(
            name = "reservation_service",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id")
    )
    private List<Service> serviceList;

    // Default constructor
    public Reservation() {}

    // Constructor for initialization
    public Reservation(String reservationNumber, LocalDateTime creationDate,
                       LocalDateTime pickUpDate, LocalDateTime dropOffDate,
                       String status, Car car, Member member, Location pickUpLocation,
                       Location dropOffLocation) {
        this.reservationNumber = reservationNumber;
        this.creationDate = creationDate;
        this.pickUpDate = pickUpDate;
        this.dropOffDate = dropOffDate;
        this.status = status;
        this.car = car;
        this.member = member;
        this.pickUpLocation = pickUpLocation;
        this.dropOffLocation = dropOffLocation;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getReservationNumber() { return reservationNumber; }
    public void setReservationNumber(String reservationNumber) { this.reservationNumber = reservationNumber; }

    public LocalDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }

    public LocalDateTime getPickUpDate() { return pickUpDate; }
    public void setPickUpDate(LocalDateTime pickUpDate) { this.pickUpDate = pickUpDate; }

    public LocalDateTime getDropOffDate() { return dropOffDate; }
    public void setDropOffDate(LocalDateTime dropOffDate) { this.dropOffDate = dropOffDate; }

    public LocalDateTime getReturnDate() { return returnDate; }
    public void setReturnDate(LocalDateTime returnDate) { this.returnDate = returnDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Car getCar() { return car; }
    public void setCar(Car car) { this.car = car; }

    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }

    public Location getPickUpLocation() { return pickUpLocation; }
    public void setPickUpLocation(Location pickUpLocation) { this.pickUpLocation = pickUpLocation; }

    public Location getDropOffLocation() { return dropOffLocation; }
    public void setDropOffLocation(Location dropOffLocation) { this.dropOffLocation = dropOffLocation; }

    public List<Equipment> getEquipmentList() { return equipmentList; }
    public void setEquipmentList(List<Equipment> equipmentList) { this.equipmentList = equipmentList; }

    public List<Service> getServiceList() { return serviceList; }
    public void setServiceList(List<Service> serviceList) { this.serviceList = serviceList; }
}
