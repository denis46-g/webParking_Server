package com.work.webParking.controller;

import com.work.webParking.model.User;
import com.work.webParking.request.LoginRequest;
import com.work.webParking.request.RegisterRequest;
import com.work.webParking.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping(value="/users")
    public ResponseEntity<List<User>> read() {
        final List<User> users = userService.readAll();

        return users != null && !users.isEmpty() ?
                new ResponseEntity<>(users, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value="/users/{id}")
    public ResponseEntity<User> read(@PathVariable(name = "id") int id){
        final User user = userService.read(id);

        return user != null ?
                new ResponseEntity<>(user, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(value="/users")
    public ResponseEntity<?> create(@RequestBody User u) {
        userService.create(u);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping(value="/users/{id}")
    public ResponseEntity<?> update(@PathVariable(name = "id") int id, @RequestBody User u){
        final boolean is_updated = userService.update(u, id);

        return is_updated ?
                new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    @DeleteMapping(value="/users/{id}")
    public ResponseEntity<?> delete(@PathVariable(name = "id") int id){
        final boolean is_deleted = userService.delete(id);

        return is_deleted ?
                new ResponseEntity<>(HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
    }

    // основные API для работы с пользователями
    @PostMapping(value="/auth/login")
    public ResponseEntity<User> login(@RequestBody LoginRequest loginRequest){
        User user = null;
        var email = loginRequest.getEmail();
        var password = loginRequest.getPassword();
        var userlist = userService.readAll();
        for(User u: userlist){
            var currentEmail = u.getEmail();
            var currentPassword = u.getPassword();
            if(Objects.equals(currentEmail, email) && Objects.equals(currentPassword, password)){
                user = u;
                break;
            }
        }
        return user != null ?
                new ResponseEntity<>(user, HttpStatus.OK):
                new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping(value="/register")
    public ResponseEntity<User> register(@RequestBody RegisterRequest registerRequest){
        var fullName = registerRequest.getFullName();
        var phoneNumber = registerRequest.getPhoneNumber();
        var email = registerRequest.getEmail();
        var password = registerRequest.getPassword();
        var repeatPassword = registerRequest.getRepeatPassword();

        if(fullName == null || email == null || password == null || !password.equals(repeatPassword))
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        for(User u: userService.readAll()){
            if(Objects.equals(u.getEmail(), email) || Objects.equals(u.getPassword(), password) ||
                    Objects.equals(u.getPhoneNumber(), phoneNumber))
                return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        User u = new User();
        u.setEmail(email);
        u.setPassword(password);
        u.setRole("owner");
        u.setFullName(fullName);
        u.setPhoneNumber(phoneNumber);

        userService.create(u);

        return new ResponseEntity<>(u, HttpStatus.OK);
    }
}