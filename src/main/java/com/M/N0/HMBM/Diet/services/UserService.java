package com.M.N0.HMBM.Diet.services;
// src/main/java/com/M/N0/services/UserService.java


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.M.N0.HMBM.Diet.entity.UserEntity;
import com.M.N0.HMBM.Diet.repository.UserRepository;


@Service
public class UserService {
    private final UserRepository userRepo;

    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    public List<UserEntity> allUsers() {
        List<UserEntity> list = new ArrayList<>();
        userRepo.findAll().forEach(list::add);
        return list;
    }
}
