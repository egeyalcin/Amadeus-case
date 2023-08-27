package org.amadeus.mapper;

import org.amadeus.dto.user.LoginModel;
import org.amadeus.dto.user.UserByTokenModel;
import org.amadeus.dto.user.UserInformationModel;
import org.amadeus.dto.user.UserModel;
import org.amadeus.entity.user.UserEntity;
import org.amadeus.entity.user.info.UserInformationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserInformationModel convertToInfoModel(UserInformationEntity userInformationEntity);

    UserModel convertToUserModel(UserEntity userEntity);


    LoginModel convertToLoginModel(UserEntity entity);

    @Mapping(source = "user.email", target = "email")
    UserByTokenModel convertToUserByTokenModel(UserInformationEntity entity);

}
