package com.example.userapp.api;

import com.example.userapp.domain.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

record AddressInput(
        @NotBlank
        @Size(min = 1, max = 16)
        String buildingNumber,
        @NotBlank
        @Size(min = 2, max = 56)
        String city,
        @Size(min = 1, max = 10)
        String flatNumber,
        @Size(max = 10)
        String postalCode,
        @NotBlank
        @Size(min = 2, max = 90)
        String street
) {
    Address toAddress() {
        return new Address(
                buildingNumber(),
                city(),
                flatNumber(),
                postalCode(),
                street()
        );
    }
}
