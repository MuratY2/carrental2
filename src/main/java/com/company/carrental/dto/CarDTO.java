package com.company.carrental.dto;

public class CarDTO {
    private String brand;
    private String model;
    private String carType;
    private String transmissionType;
    private String barcode;

    // Constructor
    public CarDTO(String brand, String model, String carType, String transmissionType, String barcode) {
        this.brand = brand;
        this.model = model;
        this.carType = carType;
        this.transmissionType = transmissionType;
        this.barcode = barcode;
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
}
