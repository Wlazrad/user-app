package com.example.userapp.api;

import lombok.Value;
import org.springframework.lang.Nullable;


@Value
class UserSearchCriteriaParams {
    @Nullable
    String nationalIdNumber;

    public UserSearchCriteriaParams(
            @Nullable String nationalIdNumber
    ) {
        this.nationalIdNumber = nationalIdNumber;
    }
}