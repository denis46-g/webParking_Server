package com.work.webParking.service;

import com.work.webParking.model.Car;

import java.util.List;

public interface CarService {
    /*
     *   Get
     * */
    List<Car> readAll();

    Car read(int id);

    /*
     *   Post
     * */
    void create(Car c);

    /*
     *   Put
     * */
    boolean update(Car c, int id);

    /*
     *   Delete
     * */
    boolean delete(int id);
}
