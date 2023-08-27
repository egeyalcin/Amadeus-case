package org.amadeus.service;



import org.amadeus.dto.user.UserByTokenModel;
import org.amadeus.dto.user.UserDetailModel;
import org.amadeus.dto.user.UserInformationModel;
import org.amadeus.exception.LocalizedException;

import javax.security.auth.message.AuthException;

public interface UserService {


    UserInformationModel createUser(UserDetailModel userDetailModel) throws LocalizedException;

    UserByTokenModel getUserByToken(String token) throws AuthException;




}
