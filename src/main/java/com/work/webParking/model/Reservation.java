package com.work.webParking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "reservations")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reservation {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "time_from", nullable = false)
    private Timestamp time_from;

    @Column(name = "time_to", nullable = false)
    private Timestamp time_to;

    @Column(name = "user_id")
    private Integer user_id;

    @Column(name = "parking_space_id")
    private Integer parking_space_id;

    public int getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Timestamp getTimeFrom(){
        return time_from;
    }

    public void setTimeFrom(Timestamp time_from){
        this.time_from = time_from;
    }

    public Timestamp getTimeTo(){
        return time_to;
    }

    public void setTimeTo(Timestamp time_to){
        this.time_to = time_to;
    }

    // foreign key ?
    public int getUserId(){
        return user_id;
    }

    public void setUserId(Integer user_id){
        this.user_id = user_id;
    }

    public int getParkingSpaceId(){
        return parking_space_id;
    }

    public void setParkingSpaceId(Integer parking_space_id){
        this.parking_space_id = parking_space_id;
    }
}
