package org.amadeus.service;


import lombok.RequiredArgsConstructor;
import org.amadeus.entity.user.UserEntity;
import org.amadeus.exception.LocalizedException;
import org.amadeus.repository.user.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserAuthService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(()->new LocalizedException("user.is.not.exist"));
        return getUserDetails(email, userEntity);
    }


    public UserDetails loadUserByUsernameWithoutUserInformation(String email) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(email).orElseThrow(()->new LocalizedException("user.is.not.exist"));
        return getUserDetails(email, userEntity);
    }

    private UserDetails getUserDetails(String email, UserEntity userEntity) {
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return new User(userEntity.getEmail(), userEntity.getPassword(), authorities);
    }


}
