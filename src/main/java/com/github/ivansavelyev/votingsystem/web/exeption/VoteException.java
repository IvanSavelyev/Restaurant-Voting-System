package com.github.ivansavelyev.votingsystem.web.exeption;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

public class VoteException extends AppException {
    public VoteException(String message) {
        super(HttpStatus.CONFLICT, message, ErrorAttributeOptions.of(MESSAGE));
    }
}
