package com.M.N0.HMBM.Diet.controller;
// src/main/java/com/M/N0/controllers/UserController.java




import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.M.N0.HMBM.Diet.entity.UserEntity;
import com.M.N0.HMBM.Diet.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<UserEntity> me() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok((UserEntity) auth.getPrincipal());
    }

    @GetMapping
    public ResponseEntity<List<UserEntity>> all() {
        return ResponseEntity.ok(userService.allUsers());
    }
}
