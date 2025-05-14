package com.M.N0.HMBM.Diet.security.config;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import com.M.N0.HMBM.Diet.security.user.UserDetailsImpl;
import com.M.N0.HMBM.Diet.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final HandlerExceptionResolver exceptionResolver;

    public JwtAuthenticationFilter(
        JwtService jwtService,
        UserDetailsService userDetailsService,
        @Qualifier("handlerExceptionResolver")
        HandlerExceptionResolver exceptionResolver
    ) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.exceptionResolver = exceptionResolver;
    }

    @Override
    protected void doFilterInternal(
        @NonNull HttpServletRequest req,
        @NonNull HttpServletResponse res,
        @NonNull FilterChain chain
    ) throws ServletException, IOException {
        String authHeader = req.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            chain.doFilter(req, res);
            return;
        }

        try {
            String token = authHeader.substring(7);
            String username = jwtService.extractUsername(token);
            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                UserDetailsImpl user = (UserDetailsImpl) userDetailsService.loadUserByUsername(username);
                if (jwtService.isTokenValid(token, user)) {
                    var authToken = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities());
                    authToken.setDetails(
                      new WebAuthenticationDetailsSource().buildDetails(req));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
            chain.doFilter(req, res);
        } catch (Exception e) {
            exceptionResolver.resolveException(req, res, null, e);
        }
    }
}
