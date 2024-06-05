package ru.albina.user.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.albina.user.dto.response.ErrorDto;
import ru.albina.user.exception.EntityNotFoundException;
import ru.albina.user.exception.UserAuthorizationException;

@RestControllerAdvice
public class WebExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class, UserAuthorizationException.class})
    public ErrorDto notFound(Exception e) {
        return new ErrorDto(e.getMessage());
    }

}
