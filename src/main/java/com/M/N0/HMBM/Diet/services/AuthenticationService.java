package com.M.N0.HMBM.Diet.services;
// src/main/java/com/M/N0/services/AuthenticationService.java



import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.M.N0.HMBM.Diet.dto.LoginUserDTO;
import com.M.N0.HMBM.Diet.dto.RegisterUserDTO;
import com.M.N0.HMBM.Diet.entity.UserEntity;

import com.M.N0.HMBM.Diet.repository.UserRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepo;
    private final AuthenticationManager authManager;
    private final PasswordEncoder encoder;


    public UserEntity signup(RegisterUserDTO dto) {
        UserEntity user = UserEntity.builder()
            .fullName(dto.getFullName())
            .email(dto.getEmail())
            .password(encoder.encode(dto.getPassword()))
            .active(dto.getActive())
            .role(dto.getRole())
            .age(dto.getAge())
            .gender(dto.getGender())
            .height(dto.getHeight())
            .weight(dto.getWeight())
            .birthdate(dto.getBirthdate())
            .activity_level(dto.getActivity_level())
            .hydration_goal(dto.getHydration_goal())
            .build();
            
        return userRepo.save(user);
    }

    public UserEntity authenticate(LoginUserDTO dto) {
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );
        return userRepo.findByEmail(dto.getEmail())
            .orElseThrow();
    }
}
