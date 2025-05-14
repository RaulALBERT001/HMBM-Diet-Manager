package com.M.N0.HMBM.Diet.security.config;
// src/main/java/com/M/N0/configs/SecurityConfiguration.java

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authProvider;

    public SecurityConfiguration(
        JwtAuthenticationFilter jwtAuthFilter,
        AuthenticationProvider authProvider
    ) {
        this.jwtAuthFilter = jwtAuthFilter;
        this.authProvider = authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
          .csrf().disable()
          .cors().and()
          .authorizeHttpRequests()
            .requestMatchers("/auth/**").permitAll()
            .anyRequest().authenticated()
          .and()
          .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
          .and()
          .authenticationProvider(authProvider)
          .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration cfg = new CorsConfiguration();
        cfg.setAllowedOrigins(List.of("http://localhost:8005"));
        cfg.setAllowedMethods(List.of("GET","POST"));
        cfg.setAllowedHeaders(List.of("Authorization","Content-Type"));
        UrlBasedCorsConfigurationSource src = new UrlBasedCorsConfigurationSource();
        src.registerCorsConfiguration("/**", cfg);
        return src;
    }
}
