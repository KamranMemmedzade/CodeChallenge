package com.bestcommerce.exception;


import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)

public class EmailAlreadyExistsException extends RuntimeException {


    public EmailAlreadyExistsException(String message) {
        super(message);
    }
}
