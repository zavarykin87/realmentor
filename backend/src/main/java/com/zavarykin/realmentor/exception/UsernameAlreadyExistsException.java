package com.zavarykin.realmentor.exception;

public class UsernameAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE = "Пользователь с таким логином: %s уже существует";

    public UsernameAlreadyExistsException(String username) {
        super(String.format(MESSAGE, username));
    }
}
