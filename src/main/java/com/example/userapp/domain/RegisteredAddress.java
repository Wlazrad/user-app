package com.example.userapp.domain;


import com.google.common.base.Strings;
import jakarta.annotation.Nullable;
import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@EqualsAndHashCode
public class RegisteredAddress {
    @NotBlank
    @Size(min = 1, max = 16)
    private String buildingNumber;

    @NotBlank
    @Size(min = 2, max = 56)
    private String city;

    @NotBlank
    @Size(min = 2, max = 64)
    private String country;

    @Access(AccessType.FIELD)
    @Size(min = 1, max = 64)
    private String district;

    @Access(AccessType.FIELD)
    @Size(min = 1, max = 24)
    private String state;

    @Access(AccessType.FIELD)
    @Size(min = 1, max = 10)
    private String flatNumber;

    @Access(AccessType.FIELD)
    @Size(max = 10)
    private String postalCode;

    @Size(min = 2, max = 90)
    @NotBlank
    private String street;

    public RegisteredAddress(
            String buildingNumber,
            String city,
            String country,
            @Nullable String district,
            @Nullable String state,
            @Nullable String flatNumber,
            @Nullable String postalCode,
            String street
    ) {
        this.buildingNumber = buildingNumber;
        this.city = city;
        this.country = country;
        this.district = Strings.emptyToNull(district);
        this.state = Strings.emptyToNull(state);
        this.flatNumber = Strings.emptyToNull(flatNumber);
        this.postalCode = postalCode;
        this.street = street;
    }

    RegisteredAddress(UserPatch.RegisteredAddressPatch registeredAddressPatch) {
        patch(registeredAddressPatch);
    }

    public Optional<String> getFlatNumber() {
        return Optional.ofNullable(flatNumber);
    }

    public Optional<String> getPostalCode() {
        return Optional.ofNullable(postalCode);
    }

    void patch(UserPatch.RegisteredAddressPatch registeredAddressPatch) {
        if (registeredAddressPatch.getBuildingNumber().isPatched()) {
            this.buildingNumber = registeredAddressPatch.getBuildingNumber().getValue();
        }

        if (registeredAddressPatch.getCity().isPatched()) {
            this.city = registeredAddressPatch.getCity().getValue();
        }

        if (registeredAddressPatch.getFlatNumber().isPatched()) {
            this.flatNumber = registeredAddressPatch.getFlatNumber().getValue();
        }

        if (registeredAddressPatch.getPostalCode().isPatched()) {
            this.postalCode = registeredAddressPatch.getPostalCode().getValue();
        }

        if (registeredAddressPatch.getStreet().isPatched()) {
            this.street = registeredAddressPatch.getStreet().getValue();
        }
    }
}
