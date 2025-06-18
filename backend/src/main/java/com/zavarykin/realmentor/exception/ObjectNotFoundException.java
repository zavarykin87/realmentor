package com.zavarykin.realmentor.exception;

public class ObjectNotFoundException extends RuntimeException {

    private static final String MESSAGE = "Объект %s не найден";

    public ObjectNotFoundException(String object) {
        super(String.format(MESSAGE, object));
    }
}
