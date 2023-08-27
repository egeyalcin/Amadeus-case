package org.amadeus.service;


import lombok.RequiredArgsConstructor;
import org.amadeus.auth.JwtUtil;
import org.amadeus.dto.user.UserByTokenModel;
import org.amadeus.dto.user.UserDetailModel;
import org.amadeus.dto.user.UserInformationModel;
import org.amadeus.entity.user.UserEntity;
import org.amadeus.entity.user.address.AddressEntity;
import org.amadeus.entity.user.info.UserInformationEntity;
import org.amadeus.exception.LocalizedException;
import org.amadeus.mapper.UserMapper;
import org.amadeus.repository.user.AddressRepository;
import org.amadeus.repository.user.UserInformationRepository;
import org.amadeus.repository.user.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final UserInformationRepository userInformationRepository;

    private final AddressRepository addressRepository;

    private final UserMapper userMapper;

    private final JwtUtil jwtUtil;


    @Override
    public UserInformationModel createUser(UserDetailModel userDetailModel) throws LocalizedException {
        UserEntity byEmail = userRepository.findByEmail(userDetailModel.getUser()
                                                                       .getEmail())
                                           .orElseThrow(() -> new LocalizedException("user.is.not.exist"));
        if (byEmail != null) {
            throw new LocalizedException("user.is.exist");
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(userDetailModel.getUser()
                                           .getEmail());
        userEntity.setPassword(new BCryptPasswordEncoder().encode(userDetailModel.getUser()
                                                                                 .getPassword()));
        userRepository.save(userEntity);

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(userDetailModel.getAddress()
                                             .getCity());
        addressEntity.setCountry(userDetailModel.getAddress()
                                                .getCountry());
        addressEntity.setTown(userDetailModel.getAddress()
                                             .getTown());
        addressRepository.save(addressEntity);

        UserInformationEntity userInformationEntity = new UserInformationEntity();
        userInformationEntity.setUser(userEntity);
        userInformationEntity.setGender(userDetailModel.getGender());
        userInformationEntity.setAddress(addressEntity);
        userInformationEntity.setFirstName(userDetailModel.getFirstName());
        userInformationEntity.setLastName(userDetailModel.getLastName());
        userInformationEntity.setImagePath(userDetailModel.getImagePath());
        userInformationEntity.setBirthDay(userDetailModel.getBirthDay());
        userInformationRepository.save(userInformationEntity);

        return userMapper.convertToInfoModel(userInformationEntity);
    }

    @Override
    public UserByTokenModel getUserByToken(String token) {
        String email = SecurityContextHolder.getContext()
                                            .getAuthentication()
                                            .getName();

        return userMapper.convertToUserByTokenModel(userInformationRepository.findByUserEmail(email)
                                                                  .orElseThrow(() -> new LocalizedException(
                                                                          "user.is.not.exist")));
    }
}

