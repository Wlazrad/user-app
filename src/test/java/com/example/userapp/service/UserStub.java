package com.example.userapp.service;

import com.example.userapp.api.NationalIdNumber;
import com.example.userapp.domain.Address;
import com.example.userapp.domain.PersonName;
import com.example.userapp.domain.RegisteredAddress;
import com.example.userapp.domain.User;

public class UserStub {

    public static User createUserStub() {
        PersonName personName = new PersonName("John", "Doe");

        NationalIdNumber nationalIdNumber = new NationalIdNumber("1234567890");

        Address address = new Address(
                "123",
                "Test City",
                null,
                "12345",
                "Test Street"
        );


        RegisteredAddress registeredAddress = new RegisteredAddress(
                "456",
                "Registered City",
                "Test Country",
                "Test District",
                "Test State",
                null,
                "67890",
                "Registered Street"
        );


        User user = User.create(personName, nationalIdNumber);
        return user;
    }
}
