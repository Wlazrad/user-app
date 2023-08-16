package com.example.userapp.domain;

import com.example.userapp.api.NationalIdNumber;

public class UserFactory {

    public static User createUser(String firstName, String lastName, String nationalId) {
        PersonName personName = new PersonName(firstName, lastName);
        NationalIdNumber nationalIdNumber = new NationalIdNumber(nationalId);
        return User.create(personName, nationalIdNumber);
    }
}
