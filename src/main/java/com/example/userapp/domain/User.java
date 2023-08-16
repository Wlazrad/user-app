package com.example.userapp.domain;

import com.example.userapp.api.NationalIdNumber;
import com.example.userapp.commons.ValidationPatterns;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@Table(name = "\"user\"")
public class User {

    @Id
    @Column(updatable = false)
    @Getter(value = AccessLevel.PACKAGE)
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private final Long id;

    @Column(nullable = false, updatable = false)
    private final UUID uuid;

    @NotNull
    @Embedded
    @Valid
    private PersonName personName;

    @AttributeOverride(name = "value", column = @Column(name = "national_id_number"))
    @Valid
    private NationalIdNumber nationalIdNumber;

    @Email(regexp = ValidationPatterns.EMAIL)
    @Size(max = 70)
    private String email;

    @Pattern(regexp = ValidationPatterns.MOBILE_PHONE)
    @Size(max = 24)
    private String mobilePhone;

    @Pattern(regexp = ValidationPatterns.MOBILE_PHONE)
    @Size(max = 24)
    private String workPhone;

    @Column(nullable = true)
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "buildingNumber", column = @Column(name = "address_building_number", nullable = true)),
            @AttributeOverride(name = "city", column = @Column(name = "address_city", nullable = true)),
            @AttributeOverride(name = "flatNumber", column = @Column(name = "address_flat_number", nullable = true)),
            @AttributeOverride(name = "postalCode", column = @Column(name = "address_postal_code", nullable = true)),
            @AttributeOverride(name = "street", column = @Column(name = "address_street", nullable = true))
    })
    private Address address;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "buildingNumber", column = @Column(name = "registered_address_building_number", nullable = true)),
            @AttributeOverride(name = "city", column = @Column(name = "registered_address_city", nullable = true)),
            @AttributeOverride(name = "flatNumber", column = @Column(name = "registered_address_flat_number", nullable = true)),
            @AttributeOverride(name = "postalCode", column = @Column(name = "registered_address_postal_code", nullable = true)),
            @AttributeOverride(name = "street", column = @Column(name = "registered_address_street", nullable = true))
    })
    private RegisteredAddress registeredAddress;

    private User(
            UUID uuid,
            PersonName personName,
            @Nullable NationalIdNumber nationalIdNumber
    ) {
        this.id = null;
        this.uuid = uuid;
        this.personName = personName;
        this.nationalIdNumber = nationalIdNumber;
    }

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    public Optional<RegisteredAddress> getRegisteredAddress() {
        return Optional.ofNullable(registeredAddress);
    }

    public Optional<String> getEmail() {
        return Optional.ofNullable(email);
    }

    public Optional<String> getMobilePhone() {
        return Optional.ofNullable(mobilePhone);
    }

    public Optional<String> getWorkPhone() {
        return Optional.ofNullable(workPhone);
    }


    public Optional<NationalIdNumber> getNationalIdNumber() {
        return Optional.ofNullable(nationalIdNumber);
    }

    public User patch(UserPatch patch) {
        patch.getAddress().ifPatched(this::patchAddress);
        patch.getRegisteredAddress().ifPatched(this::patchRegisteredAddress);
        patch.getMobilePhone().ifPatched(phone -> this.mobilePhone = phone);
        patch.getEmail().ifPatched(email -> this.email = email);
        return this;
    }

    public static User create(
            PersonName personName,
            @Nullable NationalIdNumber nationalIdNumber
    ) {
        UUID uuid = UUID.randomUUID();
        return new User(
                uuid,
                personName,
                nationalIdNumber
        );
    }

    private void patchAddress(UserPatch.AddressPatch maybePatch) {
        Optional.ofNullable(maybePatch).ifPresentOrElse(
                patch -> getAddress().ifPresentOrElse(
                        thisAddress -> thisAddress.patch(patch),
                        () -> this.address = new Address(patch)
                ),
                () -> this.address = null
        );
    }

    private void patchRegisteredAddress(UserPatch.RegisteredAddressPatch maybePatch) {
        Optional.ofNullable(maybePatch).ifPresentOrElse(
                patch -> getRegisteredAddress().ifPresentOrElse(
                        thisAddress -> thisAddress.patch(patch),
                        () -> this.registeredAddress = new RegisteredAddress(patch)
                ),
                () -> this.address = null
        );
    }

    public void assignEmail(String email) {
        this.email = email;
    }

    public void assignMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public void assignWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }
}
