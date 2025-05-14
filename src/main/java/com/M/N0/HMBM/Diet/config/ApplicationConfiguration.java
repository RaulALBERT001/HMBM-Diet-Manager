package com.M.N0.HMBM.Diet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.M.N0.HMBM.Diet.entity.UserEntity;
import com.M.N0.HMBM.Diet.repository.UserRepository;
import com.M.N0.HMBM.Diet.security.user.UserDetailsImpl;

@Configuration
public class ApplicationConfiguration {

    private final UserRepository userRepository;

    public ApplicationConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> {
            UserEntity userEntity = userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return new UserDetailsImpl(userEntity);
        };
    }


    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }
}

