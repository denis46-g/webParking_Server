package com.work.webParking.service;

import com.work.webParking.model.Reservation;
import com.work.webParking.model.User;

import java.util.List;

public interface ReservationService {
    /*
     *   Get
     * */
    List<Reservation> readAll();

    Reservation read(int id);

    /*
     *   Post
     * */
    void create(Reservation r);

    /*
     *   Put
     * */
    boolean update(Reservation r, int id);

    /*
     *   Delete
     * */
    boolean delete(int id);
}
