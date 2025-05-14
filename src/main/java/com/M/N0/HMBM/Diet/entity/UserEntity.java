package com.M.N0.HMBM.Diet.entity;





import java.time.Instant;
import java.time.LocalDate;


import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.M.N0.HMBM.Diet.entity.Enum.ActivityEnum;
import com.M.N0.HMBM.Diet.entity.Enum.GenderEnum;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Table(name="users")
@Entity
@Getter @Setter @ToString (exclude = "password") @AllArgsConstructor @NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class UserEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (nullable = false)
    private Long id;

    @Column (name = "fullName",  nullable = false, length = 120)
    private String fullName;

    @Email
    @Column (name = "email",  nullable = false, length = 100)
    private String email;

    @Column (name = "password",  nullable = false, length = 255 )
    private String password;
    
    @Column (name = "role", nullable = false)
    private String role;

    @Column (name = "status" , nullable = false)
    private Boolean active; 

    @Enumerated(EnumType.STRING)
    @Column (name = "gender", nullable = false)
    private GenderEnum gender; 

    @Column (name = "image", nullable = false)
    private String image;

    @Column (name = "height", precision = 3, nullable = false)
    private Float height;

    @Column (name = "weight", nullable = false)
    private Float weight;

    @Column (name = "age", nullable = false)
    private Integer age;

    @Column (name ="birthdate", nullable = false)
    private LocalDate birthdate;

    @CreatedDate
    @Column (name = "created_at", nullable = false)
    private Instant created_at;

    @LastModifiedDate
    @Column (name = "updated_at", nullable = false)
    private Instant updated_at;

    @Column (name = "activity_level", nullable = false)
    @Enumerated(EnumType.STRING)
    private ActivityEnum activity_level;

    @Column (name = "hydration_goal", nullable = false)
    private Integer hydration_goal;



}

