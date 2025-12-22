package com.mypack.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class Shippers {
    private Integer shipperID;

    @NotBlank(message = "ShipperName is required")
    @Size(max = 32, message = "ShipperName must be ≤ 32 characters")
    private String shipperName;

    @NotBlank(message = "Phone is required")
    @Size(max = 15, message = "Phone must be ≤ 15 characters")
    private String phone;

    public Shippers(Integer shipperID, String shipperName, String phone) {
        this.shipperID = shipperID;
        this.shipperName = shipperName;
        this.phone = phone;
    }

    public Shippers( String shipperName, String phone) {
        this.shipperName = shipperName;
        this.phone = phone;
    }
    
        public Shippers() {
        this.shipperID = 0;
        this.shipperName = null;
        this.phone = null;
    }

    public Integer getShipperID() { return shipperID; }
    public void setShipperID(Integer shipperID) { this.shipperID = shipperID; }
    public String getShipperName() { return shipperName; }
    public void setShipperName(String shipperName) { this.shipperName = shipperName; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
}
