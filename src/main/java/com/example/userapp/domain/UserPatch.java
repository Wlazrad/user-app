package com.example.userapp.domain;

import com.example.userapp.commons.Patch;
import lombok.Value;

@Value
public class UserPatch {
    Patch<AddressPatch> address;
    Patch<RegisteredAddressPatch> registeredAddress;
    Patch<String> email;
    Patch<String> mobilePhone;
    Patch<String> workPhone;

    @Value
    public static class AddressPatch {
        Patch<String> buildingNumber;
        Patch<String> city;
        Patch<String> flatNumber;
        Patch<String> postalCode;
        Patch<String> street;
    }

    @Value
    public static class RegisteredAddressPatch {
        Patch<String> buildingNumber;
        Patch<String> city;
        Patch<String> flatNumber;
        Patch<String> postalCode;
        Patch<String> street;
    }

    @Value
    public static class PersonNamePatch {
        Patch<String> firstName;
        Patch<String> lastName;
    }
}