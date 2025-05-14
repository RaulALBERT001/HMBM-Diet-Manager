// src/main/java/com/M/N0/services/JwtService.java
package com.M.N0.HMBM.Diet.services;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.M.N0.HMBM.Diet.entity.UserEntity;
import com.M.N0.HMBM.Diet.security.user.UserDetailsImpl;

import java.security.Key;
import java.util.*;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expirationMS}")
    private long jwtExpiration;

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);//extracts the subject claim from the JWT token
    }

    public String  extractId(String token) {
        return extractClaim(token, Claims::getId);//extracts the id claim from the JWT token
    }

   

    public <T> T extractClaim(String token, Function<Claims, T> resolver){//uses the Function interface to extract a specific claim from the JWT token{
        return resolver.apply(extractAllClaims(token));//applies the resolver function to the extracted claims
        //the return of extractAllClaims(token) is passed to the resolver function(which could be getId or getSubject)
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()//Creates the parserbuilder
                   .setSigningKey(getSignInKey())//sets the signing key
                   .build()//builds the parser
                   .parseClaimsJws(token)//parses and verifies the token
                   .getBody();//get the claims 
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);//decoded into a byte array
        return Keys.hmacShaKeyFor(keyBytes);//create the actual key to sign in the token
    }

    public String generateToken(UserDetailsImpl user) {
        Map<String,Object> hash = new HashMap<>();
        return Jwts.builder()/*Set the claims */
                   .setClaims(hash)
                   .setSubject(user.getUsername())
                   .setIssuedAt(new Date())
                   .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                   .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                   .compact();
    }
    

    public boolean isTokenValid(String token, UserDetailsImpl user) {
        final String username = extractUsername(token);
        return (username.equals(user.getUsername()) && 
               !extractClaim(token, Claims::getExpiration).before(new Date()));
    }

    public long getExpirationTime() {
        return jwtExpiration;
    }
}
