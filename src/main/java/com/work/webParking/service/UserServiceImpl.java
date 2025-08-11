package com.work.webParking.service;

import com.work.webParking.model.User;
import com.work.webParking.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> readAll(){
        return userRepository.findAll();
    }

    @Override
    public User read(int id){
        return userRepository.getReferenceById(id); // ?
    }

    @Override
    public void create(User u){
        userRepository.save(u);
    }

    @Override
    public boolean update(User u, int id){
        if(userRepository.existsById(id))
        {
            u.setId(id);
            userRepository.save(u);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(int id){
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
