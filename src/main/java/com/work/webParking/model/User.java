package com.work.webParking.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import org.hibernate.annotations.Check;

@Entity
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Check(constraints = "role IN ('admin', 'owner')")
public class User {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password", nullable = false, unique = true, length = 50)
    private String password;

    @Column(name = "role", nullable = false, length = 20)
    private String role;

    @Column(name = "full_name", nullable = false, length = 100)
    private String full_name;

    @Column(name = "phone_number", unique = true, length = 20)
    private String phone_number;

    public int getId(){
        return id;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getRole(){
        return role;
    }

    public void setRole(String role){
        this.role = role;
    }

    public String getFullName(){
        return full_name;
    }

    public void setFullName(String full_name){
        this.full_name = full_name;
    }

    public String getPhoneNumber(){
        return phone_number;
    }

    public void setPhoneNumber(String phone_number){
        this.phone_number = phone_number;
    }
}
