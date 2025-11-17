package com.etiya.common.crosscuttingconcerns.exceptions.types;

public class EventParsingException extends RuntimeException {
    public EventParsingException(String message) {
        super(message);
    }

    public EventParsingException(String message, Throwable cause) {
        super(message, cause);
    }}
