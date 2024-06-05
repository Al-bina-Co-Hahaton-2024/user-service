package ru.albina.user.exception;


public class UserAuthorizationException extends RuntimeException {

    public UserAuthorizationException(String username) {
        super(String.format("Cannot authorization user with: %s", username));
    }
}
