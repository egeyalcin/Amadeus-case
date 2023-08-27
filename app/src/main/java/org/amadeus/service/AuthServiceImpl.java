package org.amadeus.service;


import lombok.RequiredArgsConstructor;
import org.amadeus.auth.JwtUtil;
import org.amadeus.dto.user.LoginModel;
import org.amadeus.dto.user.PersistRegisterModel;
import org.amadeus.dto.user.UserModel;
import org.amadeus.entity.user.UserEntity;
import org.amadeus.entity.user.info.UserInformationEntity;
import org.amadeus.exception.LocalizedException;
import org.amadeus.mapper.UserMapper;
import org.amadeus.repository.user.UserInformationRepository;
import org.amadeus.repository.user.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final UserInformationRepository userInformationRepository;


    @Override
    public LoginModel login(UserModel userModel) {

        UserEntity userEntity = userRepository.findByEmail(userModel.getEmail())
                .orElseThrow(() -> new LocalizedException("user.is.not.exist"));

        checkUserPassword(userModel, userEntity);

        List<GrantedAuthority> userAuthorities = getUserAuthorities(userEntity);


        LoginModel loginModel = userMapper.convertToLoginModel(userEntity);


        UserDetails userDetails = new User(userEntity.getEmail(), userEntity.getPassword(), userAuthorities);

        loginModel.setAccessToken(jwtUtil.generateToken(userDetails, userEntity));

        return loginModel;
    }

    private List<GrantedAuthority> getUserAuthorities(UserEntity userEntity) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return authorities;
    }

    private void checkUserPassword(UserModel userModel, UserEntity userEntity) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        boolean passwordsMatch = passwordEncoder.matches(userModel.getPassword(), userEntity.getPassword());

        if (!passwordsMatch) {
            throw new LocalizedException("user.is.not.exist");
        }
    }

    @Override
    @Transactional
    public LoginModel register(PersistRegisterModel persistRegisterModel) {
        checkPasswordMatch(persistRegisterModel.getPassword(),
                persistRegisterModel.getPasswordConfirmation());
        checkUserExist(persistRegisterModel.getEmail());
        UserEntity userEntity = convertPersistToLoginModel(persistRegisterModel);
        UserEntity save = userRepository.saveAndFlush(userEntity);

        saveUserInformation(persistRegisterModel, save);


        List<GrantedAuthority> userAuthorities = getUserAuthorities(userEntity);


        LoginModel loginModel = new LoginModel();
        UserDetails userDetails = new User(userEntity.getEmail(), userEntity.getPassword(), userAuthorities);

        loginModel.setAccessToken(jwtUtil.generateToken(userDetails, userEntity));

        return loginModel;

    }

    private void saveUserInformation(PersistRegisterModel persistRegisterModel, UserEntity user) {
        UserInformationEntity userInformationEntity = new UserInformationEntity();
        userInformationEntity.setUser(user);
        userInformationEntity.setFirstName(persistRegisterModel.getFirstName());
        userInformationEntity.setLastName(persistRegisterModel.getLastName());
        userInformationEntity.setBirthDay(Date.from(Instant.now()));
        userInformationRepository.save(userInformationEntity);
    }

    private UserEntity convertPersistToLoginModel(PersistRegisterModel persistRegisterModel) {

        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(persistRegisterModel.getEmail());
        userEntity.setPassword(new BCryptPasswordEncoder().encode(persistRegisterModel
                .getPassword()));

        return userEntity;
    }

    private void checkPasswordMatch(String password, String passwordConfirmation) {
        if (!password.equals(passwordConfirmation)) {
            throw new LocalizedException("password.confirmation.error");
        }
    }

    private void checkUserExist(String email) {
        if (userRepository.findByEmail(email)
                .isPresent()) {
            throw new LocalizedException("user.is.exist");

        }

    }


}
