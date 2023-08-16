package com.example.userapp.api;

import com.example.userapp.domain.RegisteredAddress;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class RegisteredAddressOutput {
    @Schema(description = "Numer budynku", required = true)
    String buildingNumber;
    @Schema(description = "Miejscowość", required = true)
    String city;
    @Schema(description = "Numer mieszkania")
    String flatNumber;
    @Schema(description = "Kod pocztowy")
    String postalCode;
    @Schema(description = "Ulica", required = true)
    String street;

    static RegisteredAddressOutput from(RegisteredAddress registeredAddress) {
        return new RegisteredAddressOutput(
                registeredAddress.getBuildingNumber(),
                registeredAddress.getCity(),
                registeredAddress.getFlatNumber().orElse(null),
                registeredAddress.getPostalCode().orElse(null),
                registeredAddress.getStreet()
        );
    }
}
