package com.marmuz.wheatherapi.controller;

import com.marmuz.wheatherapi.exception.DataIsIncorrectException;
import com.marmuz.wheatherapi.exception.DataNotFoundException;
import com.marmuz.wheatherapi.exception.ExceptionBody;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionBody handleDataNotFound(DataNotFoundException e) {
        return new ExceptionBody(e.getMessage());
    }

    @ExceptionHandler(DataIsIncorrectException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionBody handleIsIncorrect(DataIsIncorrectException e) {
        return new ExceptionBody(e.getMessage());
    }


}
