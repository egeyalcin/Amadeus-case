package org.amadeus.controller;

import lombok.RequiredArgsConstructor;
import org.amadeus.dto.user.LoginModel;
import org.amadeus.dto.user.PersistRegisterModel;
import org.amadeus.dto.user.UserModel;
import org.amadeus.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/auth")
@CrossOrigin("*")
public class AuthController {

    private final AuthService loginService;

    @PostMapping(value = "/login")
    public ResponseEntity<LoginModel> login(@RequestBody @Valid UserModel userModel) {
        return ResponseEntity.ok(loginService.login(userModel));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<LoginModel> register(@RequestBody @Valid PersistRegisterModel persistRegisterModel) {
        return ResponseEntity.ok(loginService.register(persistRegisterModel));
    }
}
