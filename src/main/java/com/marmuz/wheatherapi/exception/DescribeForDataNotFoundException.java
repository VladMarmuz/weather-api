package com.marmuz.wheatherapi.exception;

public enum DescribeForDataNotFoundException {
    NOT_FOUND_EXCEPTION("Not found ");

    private final String message;

    DescribeForDataNotFoundException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
