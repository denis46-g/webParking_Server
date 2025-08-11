package com.work.webParking.request;

public class AddCarRequest {
    private String brand;
    private String licensePlate;
    private int ownerId;

    public String getBrand(){
        return brand;
    }

    public String getLicensePlate(){
        return licensePlate;
    }

    public int getOwnerId(){
        return ownerId;
    }
}
