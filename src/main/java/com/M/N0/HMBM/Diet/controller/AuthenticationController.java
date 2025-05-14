package com.M.N0.HMBM.Diet.controller;
// src/main/java/com/M/N0/controllers/AuthenticationController.java


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.M.N0.HMBM.Diet.dto.LoginUserDTO;
import com.M.N0.HMBM.Diet.dto.RegisterUserDTO;
import com.M.N0.HMBM.Diet.entity.UserEntity;
import com.M.N0.HMBM.Diet.responses.LoginResponse;
import com.M.N0.HMBM.Diet.security.user.UserDetailsImpl;
import com.M.N0.HMBM.Diet.services.AuthenticationService;
import com.M.N0.HMBM.Diet.services.JwtService;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationService authService;
    private final JwtService jwtService;

    public AuthenticationController(
        AuthenticationService authService,
        JwtService jwtService
    ) {
        this.authService = authService;
        this.jwtService = jwtService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserEntity> signup(@RequestBody RegisterUserDTO dto) {
        return ResponseEntity.ok(authService.signup(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginUserDTO dto) {
        UserEntity user = authService.authenticate(dto);
        UserDetailsImpl userdetails = new UserDetailsImpl(user);
        String token = jwtService.generateToken(userdetails);
        return ResponseEntity.ok(new LoginResponse(token, jwtService.getExpirationTime()));
    }
}
