package com.zavarykin.realmentor.exception;

public class UserAlreadyExistException extends Exception {

    private static String message = "Пользователь с таким логином уже существует";

    public UserAlreadyExistException() {
        super(message);
    }
}
