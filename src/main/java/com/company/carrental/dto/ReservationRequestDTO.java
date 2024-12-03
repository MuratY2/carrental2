package com.company.carrental.dto;

import java.util.List;

public class ReservationRequestDTO {
    private String carBarcode;
    private int dayCount;
    private Long memberId;
    private String pickUpLocationCode;
    private String dropOffLocationCode;
    private List<String> equipmentCodes;

    // Constructors
    public ReservationRequestDTO() {}

    public ReservationRequestDTO(String carBarcode, int dayCount, Long memberId,
                                 String pickUpLocationCode, String dropOffLocationCode,
                                 List<String> equipmentCodes) {
        this.carBarcode = carBarcode;
        this.dayCount = dayCount;
        this.memberId = memberId;
        this.pickUpLocationCode = pickUpLocationCode;
        this.dropOffLocationCode = dropOffLocationCode;
        this.equipmentCodes = equipmentCodes;
    }

    // Getters and Setters
    public String getCarBarcode() { return carBarcode; }
    public void setCarBarcode(String carBarcode) { this.carBarcode = carBarcode; }

    public int getDayCount() { return dayCount; }
    public void setDayCount(int dayCount) { this.dayCount = dayCount; }

    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }

    public String getPickUpLocationCode() { return pickUpLocationCode; }
    public void setPickUpLocationCode(String pickUpLocationCode) { this.pickUpLocationCode = pickUpLocationCode; }

    public String getDropOffLocationCode() { return dropOffLocationCode; }
    public void setDropOffLocationCode(String dropOffLocationCode) { this.dropOffLocationCode = dropOffLocationCode; }

    public List<String> getEquipmentCodes() { return equipmentCodes; }
    public void setEquipmentCodes(List<String> equipmentCodes) { this.equipmentCodes = equipmentCodes; }

}
