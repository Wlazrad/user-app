package com.example.userapp.api;

import com.example.userapp.domain.User;
import lombok.Value;

import java.util.UUID;

@Value
public class UserOutput {

    UUID uuid;
    String firstName;
    String lastName;
    AddressOutput address;
    RegisteredAddressOutput registeredAddress;
    String email;
    String nationalIdNumber;
    String mobilePhone;

    static UserOutput from(User user) {
        return new UserOutput(
                user.getUuid(),
                user.getPersonName().getFirstName(),
                user.getPersonName().getLastName(),
                user.getAddress().map(AddressOutput::from).orElse(null),
                user.getRegisteredAddress().map(RegisteredAddressOutput::from).orElse(null),
                user.getEmail().orElse(null),
                user.getNationalIdNumber().map(NationalIdNumber::toString).orElse(null),
                user.getMobilePhone().orElse(null)
        );
    }
}
