package com.example.userapp.commons;


import com.example.userapp.api.NationalIdNumber;

import java.util.UUID;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException(UUID uuid) {
        super(String.format("User with uuid '%s' not exists.", uuid));
    }

    public UserNotFoundException(NationalIdNumber nationalIdNumber) {
        super(String.format(
                "User with national id number '%s' not exists.",
                nationalIdNumber.toString()
        ));
    }
}
