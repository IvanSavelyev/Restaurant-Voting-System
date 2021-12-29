package ru.graduation.web.exeption;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.http.HttpStatus;

import static org.springframework.boot.web.error.ErrorAttributeOptions.Include.MESSAGE;

public class NotChangeYouMindException extends AppException{
    public NotChangeYouMindException(String message) {
        super(HttpStatus.FORBIDDEN, message, ErrorAttributeOptions.of(MESSAGE));
    }
}
