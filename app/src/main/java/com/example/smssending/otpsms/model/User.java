package com.example.smssending.otpsms.model;

public class User {
    public String firstName;
    public String lastName;
    public String phoneNo;
    public String otp;

    public User(String firstName, String lastName, String phoneNo, String otp) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNo = phoneNo;
        this.otp = otp;
    }

    public String getFirstName() {

        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public String getOtp() {
        return otp;
    }
}