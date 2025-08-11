package com.work.webParking.request;

public class RegisterRequest {
    private String fullName;
    private String phoneNumber;
    private String email;
    private String password;
    private String repeatPassword;

    public String getFullName(){
        return fullName;
    }

    public String getPhoneNumber(){
        return phoneNumber;
    }

    public String getEmail(){
        return email;
    }

    public String getPassword(){
        return password;
    }

    public String getRepeatPassword(){
        return repeatPassword;
    }

}
