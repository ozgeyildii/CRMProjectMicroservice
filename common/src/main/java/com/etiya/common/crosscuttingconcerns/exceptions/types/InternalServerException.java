package com.etiya.common.crosscuttingconcerns.exceptions.types;

public class InternalServerException  extends RuntimeException {
    public InternalServerException(String message) {
        super(message);
    }
}
