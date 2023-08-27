package org.amadeus.controller;

import lombok.RequiredArgsConstructor;
import org.amadeus.dto.user.UserByTokenModel;
import org.amadeus.dto.user.UserDetailModel;
import org.amadeus.dto.user.UserInformationModel;
import org.amadeus.exception.LocalizedException;
import org.amadeus.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.message.AuthException;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user")
@CrossOrigin("*")
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<UserInformationModel> createUser(
            @RequestBody @Valid UserDetailModel userDetailModel) throws LocalizedException {
        return new ResponseEntity<>(userService.createUser(userDetailModel), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<UserByTokenModel> getUserByToken(@RequestHeader("Authorization") String token) throws AuthException {
        return new ResponseEntity<>(userService.getUserByToken(token), HttpStatus.OK);
    }

}
