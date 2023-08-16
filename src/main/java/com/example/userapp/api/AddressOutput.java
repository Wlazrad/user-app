package com.example.userapp.api;

import com.example.userapp.domain.Address;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Value;

@Value
public class AddressOutput {
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

    static AddressOutput from(Address address) {
        return new AddressOutput(
                address.getBuildingNumber(),
                address.getCity(),
                address.getFlatNumber().orElse(null),
                address.getPostalCode().orElse(null),
                address.getStreet()
        );
    }
}
