package com.M.N0.HMBM.Diet.exceptions;



import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleAll(Exception ex) {
        ProblemDetail detail;
        if (ex instanceof BadCredentialsException) {
            detail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
            detail.setProperty("description", "Usuário ou senha incorretos");
            return detail;
        }
        if (ex instanceof AccountStatusException) {
            detail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
            detail.setProperty("description", "Conta bloqueada");
            return detail;
        }
        if (ex instanceof AccessDeniedException) {
            detail = ProblemDetail.forStatusAndDetail(HttpStatus.FORBIDDEN, ex.getMessage());
            detail.setProperty("description", "Acesso negado");
            return detail;
        }
        if (ex instanceof SignatureException) {
            detail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, ex.getMessage());
            detail.setProperty("description", "Assinatura JWT inválida");
            return detail;
        }
        if (ex instanceof ExpiredJwtException) {
            detail = ProblemDetail.forStatusAndDetail(HttpStatus.valueOf(401), ex.getMessage());
            detail.setProperty("description", "Token JWT expirado");
            return detail;
        }
        detail = ProblemDetail.forStatusAndDetail(HttpStatus.valueOf(500), "Erro interno");
        detail.setProperty("description", ex.getMessage());
        return detail;
    }
}
