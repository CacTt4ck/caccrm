package com.cactt4ck.caccrm.controller;

import com.cactt4ck.caccrm.model.CacAccount;
import com.cactt4ck.caccrm.model.LoginResponse;
import com.cactt4ck.caccrm.model.LoginUserDto;
import com.cactt4ck.caccrm.model.RegisterUserDto;
import com.cactt4ck.caccrm.service.AuthentificationService;
import com.cactt4ck.caccrm.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthentificationController {

    private final JwtService jwtService;

    private final AuthentificationService authenticationService;

    public AuthentificationController(JwtService jwtService, AuthentificationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<CacAccount> register(@RequestBody RegisterUserDto registerUserDto) {
        CacAccount registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto) {
        CacAccount authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = LoginResponse.builder().token(jwtToken).expiresIn(jwtService.getExpirationTime()).build();

        return ResponseEntity.ok(loginResponse);
    }

}
