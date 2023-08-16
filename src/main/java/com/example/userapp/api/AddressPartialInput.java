package com.example.userapp.api;

import com.example.userapp.commons.Patch;
import com.example.userapp.commons.ValidationPatterns;
import com.example.userapp.domain.UserPatch;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
class AddressPartialInput {
    @Schema(description = "Numer budynku")
    @NotBlank
    @Size(min = 1, max = 16)
    String buildingNumber;

    @Schema(description = "Miejscowość")
    @NotBlank
    @Size(min = 2, max = 56)
    String city;

    @Schema(description = "Numer mieszkania")
    @NotBlank
    @Size(min = 1, max = 10)
    String flatNumber;

    @Schema(description = "Kod pocztowy", example = "21-107")
    @Pattern(regexp = ValidationPatterns.POSTAL_CODE)
    @Size(max = 10)
    @NotBlank
    String postalCode;

    @Schema(description = "Ulica")
    @NotBlank
    @Size(min = 2, max = 90)
    String street;

    UserPatch.AddressPatch toPatch() {
        return new UserPatch.AddressPatch(
                buildingNumber != null ? Patch.of(buildingNumber) : Patch.empty(),
                city != null ? Patch.of(city) : Patch.empty(),
                flatNumber != null ? Patch.of(flatNumber) : Patch.empty(),
                postalCode != null ? Patch.of(postalCode) : Patch.empty(),
                street != null ? Patch.of(street) : Patch.empty()
        );
    }
}
