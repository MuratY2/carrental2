package com.company.carrental.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String barcode;
    private String licensePlate;
    private String brand;
    private String model;
    private String transmissionType;
    private String carType;
    private int passengerCapacity;
    private double dailyPrice;
    private String status;

    @OneToMany(mappedBy = "car", cascade = CascadeType.PERSIST)
    private List<Reservation> reservations;

    // Default constructor
    public Car() {}

    // Constructor for initialization
    public Car(String barcode, String licensePlate, String brand, String model,
               String transmissionType, String carType, int passengerCapacity,
               double dailyPrice, String status) {
        this.barcode = barcode;
        this.licensePlate = licensePlate;
        this.brand = brand;
        this.model = model;
        this.transmissionType = transmissionType;
        this.carType = carType;
        this.passengerCapacity = passengerCapacity;
        this.dailyPrice = dailyPrice;
        this.status = status;
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBarcode() { return barcode; }
    public void setBarcode(String barcode) { this.barcode = barcode; }

    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getTransmissionType() { return transmissionType; }
    public void setTransmissionType(String transmissionType) { this.transmissionType = transmissionType; }

    public String getCarType() { return carType; }
    public void setCarType(String carType) { this.carType = carType; }

    public int getPassengerCapacity() { return passengerCapacity; }
    public void setPassengerCapacity(int passengerCapacity) { this.passengerCapacity = passengerCapacity; }

    public double getDailyPrice() { return dailyPrice; }
    public void setDailyPrice(double dailyPrice) { this.dailyPrice = dailyPrice; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public List<Reservation> getReservations() { return reservations; }
    public void setReservations(List<Reservation> reservations) { this.reservations = reservations; }
}
