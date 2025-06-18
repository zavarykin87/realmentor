package com.zavarykin.realmentor.exception;

public class TokenExpiredException extends RuntimeException {

    private static final String MESSAGE = "Срок действия токена истек";

    public TokenExpiredException() {
        super(MESSAGE);
    }
}
