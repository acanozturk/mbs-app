package com.group5.mbs.controllers;

import com.group5.mbs.api.dtos.*;
import com.group5.mbs.api.model.request.LoginRequest;
import com.group5.mbs.api.model.response.LoginResponse;
import com.group5.mbs.entities.UserType;
import com.group5.mbs.services.interfaces.*;
import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/mbs/login")
    public LoginResponse loginWithUserType(@Valid @RequestBody final LoginRequest request) {
        final String email = request.getEmail();
        final String password = request.getPassword();
        final UserType userType = request.getUserType();

        final User user = loginService.userLoginRequest(email, password, userType);

        final LoginResponse response = new LoginResponse();

        response.setUser(user);

        return response;
    }

}
