package com.example.userapp.api;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class NationalIdNumber {

    @NotBlank
    private final String value;

    public NationalIdNumber(String value) {
        this.value = value;
    }

    public static NationalIdNumber of(String value) {
        return new NationalIdNumber(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
