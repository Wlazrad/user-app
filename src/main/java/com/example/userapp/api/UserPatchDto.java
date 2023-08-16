package com.example.userapp.api;

import com.example.userapp.commons.Patch;
import com.example.userapp.domain.UserPatch;
import lombok.Value;

@Value
public class UserPatchDto {

    Patch<UserPatch.AddressPatch> address;
    Patch<UserPatch.RegisteredAddressPatch> registeredAddress;
    Patch<String> email;
    Patch<String> mobilePhone;
    Patch<String> workPhone;
}
