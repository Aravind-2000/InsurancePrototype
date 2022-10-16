package com.example.insuranceprototype.Auth.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PasswordChangeRequest {
    
    @NotBlank
    public String email;

    @NotBlank
    public String password;


}
