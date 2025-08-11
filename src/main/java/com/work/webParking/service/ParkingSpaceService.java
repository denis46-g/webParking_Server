package com.work.webParking.service;

import com.work.webParking.model.ParkingSpace;
import com.work.webParking.model.User;

import java.util.List;

public interface ParkingSpaceService {
    /*
     *   Get
     * */
    List<ParkingSpace> readAll();

    ParkingSpace read(int id);

    /*
     *   Post
     * */
    void create(ParkingSpace p);

    /*
     *   Put
     * */
    boolean update(ParkingSpace p, int id);

    /*
     *   Delete
     * */
    boolean delete(int id);
}
