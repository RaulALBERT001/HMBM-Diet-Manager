package com.M.N0.HMBM.Diet.dto;


import java.time.LocalDate;

import com.M.N0.HMBM.Diet.entity.Enum.ActivityEnum;
import com.M.N0.HMBM.Diet.entity.Enum.GenderEnum;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

@Value
public class RegisterUserDTO {
    @NotBlank @Email
    private String email;
    @NotBlank @Size(min = 8)
    private String password;
    @NotBlank
    private String fullName;
    @NotNull
    private String role;
    @NotNull
    private Boolean active;
    @NotNull @NotBlank
    private GenderEnum gender;
    // Use String for DTO, map to GenderEnum in entity
    private String image;
    @NotNull
    private Float height;
    @NotNull
    private Float weight;
    @NotNull
    private Integer age;
    @NotNull
    private LocalDate birthdate;
    private ActivityEnum activity_level; // Use String for DTO, map to ActivityEnum in entity
    private Integer hydration_goal;
}
