package org.amadeus.service;


import org.amadeus.dto.user.LoginModel;
import org.amadeus.dto.user.PersistRegisterModel;
import org.amadeus.dto.user.UserModel;

public interface AuthService {
    LoginModel login(UserModel userModel);

    LoginModel register(PersistRegisterModel persistRegisterModel);

}
