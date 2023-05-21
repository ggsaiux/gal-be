package com.asociatialocatari.gal.base.exception;

import org.hibernate.QueryException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandler {

    @ExceptionHandler(QueryException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public QueryException handleQueryException(QueryException ce) {
        return ce;
    }

}