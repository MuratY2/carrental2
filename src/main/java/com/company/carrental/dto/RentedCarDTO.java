package com.company.carrental.dto;

import java.time.LocalDateTime;

public class RentedCarDTO {
    private String brand;
    private String model;
    private String carType;
    private String transmissionType;
    private String barcode;
    private String reservationNumber;
    private String memberName;
    private LocalDateTime dropOffDateTime;
    private String dropOffLocationName;
    private long dayCount; // Changed to `long` to match `ChronoUnit.DAYS` result

    // Constructor
    public RentedCarDTO(String brand, String model, String carType, String transmissionType,
                        String barcode, String reservationNumber, String memberName,
                        LocalDateTime dropOffDateTime, String dropOffLocationName, long dayCount) {
        this.brand = brand;
        this.model = model;
        this.carType = carType;
        this.transmissionType = transmissionType;
        this.barcode = barcode;
        this.reservationNumber = reservationNumber;
        this.memberName = memberName;
        this.dropOffDateTime = dropOffDateTime;
        this.dropOffLocationName = dropOffLocationName;
        this.dayCount = dayCount;
    }

    // Getters and Setters
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public String getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(String transmissionType) {
        this.transmissionType = transmissionType;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public LocalDateTime getDropOffDateTime() {
        return dropOffDateTime;
    }

    public void setDropOffDateTime(LocalDateTime dropOffDateTime) {
        this.dropOffDateTime = dropOffDateTime;
    }

    public String getDropOffLocationName() {
        return dropOffLocationName;
    }

    public void setDropOffLocationName(String dropOffLocationName) {
        this.dropOffLocationName = dropOffLocationName;
    }

    public long getDayCount() {
        return dayCount;
    }

    public void setDayCount(long dayCount) {
        this.dayCount = dayCount;
    }
}
