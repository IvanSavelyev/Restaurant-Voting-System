package ru.graduation.web.exeption;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

public class MultiplyVoteException extends AppException {
    public MultiplyVoteException(String message) {
        super(HttpStatus.LOCKED, message, ErrorAttributeOptions.of(MESSAGE));
    }
}
