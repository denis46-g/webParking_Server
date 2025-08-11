package com.work.webParking.service;

import com.work.webParking.model.User;

import java.util.List;

public interface UserService {

    /*
     *   Get
     * */
    List<User> readAll();

    User read(int id);

    /*
     *   Post
     * */
    void create(User u);

    /*
     *   Put
     * */
    boolean update(User u, int id);

    /*
     *   Delete
     * */
    boolean delete(int id);
}
