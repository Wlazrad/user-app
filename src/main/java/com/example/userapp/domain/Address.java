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
public class Address {
    @NotBlank
    @Size(min = 1, max = 16)
    private String buildingNumber;

    @NotBlank
    @Size(min = 2, max = 56)
    private String city;

    @Access(AccessType.FIELD)
    @Size(min = 1, max = 10)
    private String flatNumber;

    @Access(AccessType.FIELD)
    @Size(max = 10)
    private String postalCode;

    @Size(min = 2, max = 90)
    @NotBlank
    private String street;

    public Address(
            String buildingNumber,
            String city,
            @Nullable String flatNumber,
            @Nullable String postalCode,
            String street
    ) {
        this.buildingNumber = buildingNumber;
        this.city = city;
        this.flatNumber = Strings.emptyToNull(flatNumber);
        this.postalCode = postalCode;
        this.street = street;
    }

    Address(UserPatch.AddressPatch addressPatch) {
        patch(addressPatch);
    }

    public Optional<String> getFlatNumber() {
        return Optional.ofNullable(flatNumber);
    }

    public Optional<String> getPostalCode() {
        return Optional.ofNullable(postalCode);
    }

    void patch(UserPatch.AddressPatch addressPatch) {
        if (addressPatch.getBuildingNumber().isPatched()) {
            this.buildingNumber = addressPatch.getBuildingNumber().getValue();
        }

        if (addressPatch.getCity().isPatched()) {
            this.city = addressPatch.getCity().getValue();
        }

        if (addressPatch.getFlatNumber().isPatched()) {
            this.flatNumber = addressPatch.getFlatNumber().getValue();
        }

        if (addressPatch.getPostalCode().isPatched()) {
            this.postalCode = addressPatch.getPostalCode().getValue();
        }

        if (addressPatch.getStreet().isPatched()) {
            this.street = addressPatch.getStreet().getValue();
        }

    }
}
