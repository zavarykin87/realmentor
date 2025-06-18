package com.zavarykin.realmentor.exception;

public class ArgumentInvalidException extends RuntimeException {

    private static final String MESSAGE = "Недопустимое значение в параметре %s";

    public ArgumentInvalidException(String parameter) {
        super(String.format(MESSAGE, parameter));
    }
}
