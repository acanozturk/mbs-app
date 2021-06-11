package com.group5.mbs.api.model.request;

import com.group5.mbs.entities.UserType;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class LoginRequest {

    @Email
    private String email;

    @NotBlank
    private String password;

    @NotNull
    private UserType userType;

}
