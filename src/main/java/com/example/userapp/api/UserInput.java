package com.example.userapp.api;

import com.example.userapp.application.SaveUserDto;
import com.example.userapp.commons.ValidationPatterns;
import com.example.userapp.domain.PersonName;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.util.Optional;

public record UserInput(
        @Schema(description = "Pierwsze imię użytkownika")
        @NotBlank
        @Size(min = 2, max = 60)
        String firstName,
        @Schema(description = "Nazwisko użytkownika")
        @NotBlank
        @Size(min = 2, max = 60)
        String lastName,
        @Schema(description = "Numer PESEL użytkownika")
        @Pattern(regexp = ValidationPatterns.ONLY_DIGITS_LETTERS_AND_DASHES)
        @Size(min = 11, max = 11)
        String nationalIdNumber
) {
    SaveUserDto toDto() {
        return SaveUserDto.builder()
                .personName(new PersonName(firstName(), lastName()))
                .nationalIdNumber(Optional.ofNullable(nationalIdNumber).map(NationalIdNumber::of).orElse(null))
                .build();
    }
}
