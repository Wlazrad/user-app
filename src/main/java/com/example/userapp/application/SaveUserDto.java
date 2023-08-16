package com.example.userapp.application;

import com.example.userapp.api.NationalIdNumber;
import com.example.userapp.domain.Address;
import com.example.userapp.domain.PersonName;
import lombok.Builder;
import lombok.Value;
import org.springframework.lang.Nullable;

import java.util.Optional;

@Value
@Builder
public class SaveUserDto {

    PersonName personName;
    @Nullable
    Address address;
    @Nullable
    String email;
    @Nullable
    String mobilePhone;
    @Nullable
    NationalIdNumber nationalIdNumber;

    public Optional<Address> getAddress() {
        return Optional.ofNullable(address);
    }

    public Optional<NationalIdNumber> getNationalIdNumber() {
        return Optional.ofNullable(nationalIdNumber);
    }

    @Value
    public static class ExternalIdentifierDto {
        String system;
        String value;
    }
}
