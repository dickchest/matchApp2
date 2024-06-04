package com.match.validation;

public class InvalidTelegramHashException extends RuntimeException{
    public InvalidTelegramHashException(String message) {
        super(message);
    }
}
