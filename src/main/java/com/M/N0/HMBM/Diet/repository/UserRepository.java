package com.M.N0.HMBM.Diet.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.M.N0.HMBM.Diet.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);
    
}
