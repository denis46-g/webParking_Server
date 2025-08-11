package com.work.webParking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "cars")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Car {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "brand", nullable = false, length = 100)
    private String brand;

    @Column(name = "license_plate", nullable = false, unique = true, length = 20)
    private String license_plate;

    @Column(name = "owner_id")
    private Integer owner_id;

    public int getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getBrand(){
        return brand;
    }

    public void setBrand(String brand){
        this.brand = brand;
    }

    public String getLicensePlate(){
        return license_plate;
    }

    public void setLicensePlate(String license_plate){
        this.license_plate = license_plate;
    }

    // foreign key ?
    public int getOwnerId(){
        return owner_id;
    }

    public void setOwnerId(Integer owner_id){
        this.owner_id = owner_id;
    }
}
