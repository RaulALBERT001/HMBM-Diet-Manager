package com.M.N0.HMBM.Diet.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class LoginResponse {
    private String token;
    private long expiresIn;
}
