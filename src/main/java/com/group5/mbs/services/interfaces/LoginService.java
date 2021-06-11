package com.group5.mbs.services.interfaces;

import com.group5.mbs.api.dtos.User;
import com.group5.mbs.entities.UserType;

public interface LoginService {

    User userLoginRequest(String email, String password, UserType userType);

}
