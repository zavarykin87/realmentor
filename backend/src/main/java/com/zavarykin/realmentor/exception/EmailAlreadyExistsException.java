package com.zavarykin.realmentor.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    private static final String MESSAGE = "Пользователь с таким email: %s уже существует";

    public EmailAlreadyExistsException(String email) {
        super(String.format(MESSAGE, email));
    }
}
