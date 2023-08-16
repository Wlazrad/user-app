package com.example.userapp.api;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class NationalIdNumberInput {
    private String nationalIdNumber;

    public NationalIdNumber toNationalIdNumber() {
        return NationalIdNumber.of(nationalIdNumber);
    }
}
