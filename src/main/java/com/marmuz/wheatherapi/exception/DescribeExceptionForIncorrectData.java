package com.marmuz.wheatherapi.exception;

public enum DescribeExceptionForIncorrectData {
    INCORRECT_DATA("Data is not correct");

    private final String message;

    DescribeExceptionForIncorrectData(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
