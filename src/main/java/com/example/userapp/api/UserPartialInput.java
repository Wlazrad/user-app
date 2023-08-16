package com.example.userapp.api;

import com.example.userapp.commons.Patch;
import com.example.userapp.commons.ValidationPatterns;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Value;

@Value
public class UserPartialInput {

    @Schema(description = "Adres użytkownika")
    @NotNull
    AddressPartialInput address;

    @Schema(description = "Adres zameldowania użytkownika")
    @NotNull
    RegisteredAddressInput registeredAddress;

    @Schema(description = "Adres email użytkownika")
    @Email(regexp = ValidationPatterns.EMAIL)
    @Size(max = 70)
    @NotNull
    String email;

    @Schema(description = "Numer telefonu użytkownika", example = "+48500100200")
    @Pattern(regexp = ValidationPatterns.MOBILE_PHONE)
    @Size(max = 24)
    @NotBlank
    String mobilePhone;

    @Schema(description = "Numer służbowy telefonu użytkownika", example = "+48500100200")
    @Pattern(regexp = ValidationPatterns.MOBILE_PHONE)
    @Size(max = 24)
    @NotBlank
    String workPhone;

    UserPatchDto toDto() {
        return new UserPatchDto(
                address != null ? Patch.of(address.toPatch()) : Patch.empty(),
                registeredAddress != null ? Patch.of(registeredAddress.toPatch()) : Patch.empty(),
                email != null ? Patch.of(email) : Patch.empty(),
                mobilePhone != null ? Patch.of(mobilePhone) : Patch.empty(),
                workPhone != null ? Patch.of(workPhone) : Patch.empty()
        );
    }
}
