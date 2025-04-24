package com.zavarykin.realmentor.exception;

public class EmailAlreadyExistException extends Exception {

    private static final String message = "Пользователь с такой почтой уже зарегистрирован";

    public EmailAlreadyExistException() {
        super(message);
    }
}
