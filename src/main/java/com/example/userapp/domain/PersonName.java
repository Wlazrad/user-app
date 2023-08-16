package com.example.userapp.domain;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Value;

@Embeddable
@Value
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class PersonName {

    @NotBlank
    @Size(min = 2, max = 60)
    String firstName;

    @NotBlank
    @Size(min = 2, max = 60)
    String lastName;

    public PersonName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
