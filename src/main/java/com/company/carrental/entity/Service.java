package com.company.carrental.entity;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code; // Unique code for service
    private String name;
    private double price;

    @ManyToMany(mappedBy = "serviceList")
    private List<Reservation> reservations;

    // Default constructor
    public Service() {}

    // Constructor
    public Service(String code, String name, double price) {
        this.code = code;
        this.name = name;
        this.price = price;
    }

    // Getters and Setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public List<Reservation> getReservations() { return reservations; }
    public void setReservations(List<Reservation> reservations) { this.reservations = reservations; }
}
