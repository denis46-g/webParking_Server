package com.work.webParking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

@Entity
@Table(name = "parking_spaces")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ParkingSpace {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "location", nullable = false, unique = true, length = 5)
    private String location;

    @Column(name = "is_occupied", nullable = false, columnDefinition = "BOOLEAN DEFAULT FALSE")
    private boolean is_occupied;

    public int getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getLocation(){
        return location;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public boolean getIsOccupied(){
        return is_occupied;
    }

    public void setLicensePlate(boolean is_occupied){
        this.is_occupied = is_occupied;
    }
}
