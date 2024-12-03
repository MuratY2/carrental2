package com.company.carrental.dto;

import java.time.LocalDateTime;

public class ReservationResponseDTO {
    private String reservationNumber;
    private LocalDateTime pickUpDateTime;
    private LocalDateTime dropOffDateTime;
    private String pickUpLocationCode;
    private String pickUpLocationName;
    private String dropOffLocationCode;
    private String dropOffLocationName;
    private double totalAmount;

    // Constructors
    public ReservationResponseDTO() {}

    // Getters and Setters
    public String getReservationNumber() { return reservationNumber; }
    public void setReservationNumber(String reservationNumber) { this.reservationNumber = reservationNumber; }

    public LocalDateTime getPickUpDateTime() { return pickUpDateTime; }
    public void setPickUpDateTime(LocalDateTime pickUpDateTime) { this.pickUpDateTime = pickUpDateTime; }

    public LocalDateTime getDropOffDateTime() { return dropOffDateTime; }
    public void setDropOffDateTime(LocalDateTime dropOffDateTime) { this.dropOffDateTime = dropOffDateTime; }

    public String getPickUpLocationCode() { return pickUpLocationCode; }
    public void setPickUpLocationCode(String pickUpLocationCode) { this.pickUpLocationCode = pickUpLocationCode; }

    public String getPickUpLocationName() { return pickUpLocationName; }
    public void setPickUpLocationName(String pickUpLocationName) { this.pickUpLocationName = pickUpLocationName; }

    public String getDropOffLocationCode() { return dropOffLocationCode; }
    public void setDropOffLocationCode(String dropOffLocationCode) { this.dropOffLocationCode = dropOffLocationCode; }

    public String getDropOffLocationName() { return dropOffLocationName; }
    public void setDropOffLocationName(String dropOffLocationName) { this.dropOffLocationName = dropOffLocationName; }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
}
