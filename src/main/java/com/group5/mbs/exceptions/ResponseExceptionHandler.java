package com.group5.mbs.exceptions;

import com.group5.mbs.api.model.response.ExceptionResponse;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.InputMismatchException;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NullPointerException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleNullPointerException(RuntimeException exception) {
        final ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setSuccess(false);
        exceptionResponse.setErrorMessage(exception.getMessage());

        return exceptionResponse;
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ExceptionResponse handleResourceNotFoundException(RuntimeException exception) {
        final ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setSuccess(false);
        exceptionResponse.setErrorMessage(exception.getMessage());

        return exceptionResponse;
    }

    @ExceptionHandler(value = {InputMismatchException.class})
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ExceptionResponse handleInputMismatchException(RuntimeException exception) {
        final ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setSuccess(false);
        exceptionResponse.setErrorMessage(exception.getMessage());

        return exceptionResponse;
    }

    @ExceptionHandler(value = {IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleIllegalArgumentException(RuntimeException exception) {
        final ExceptionResponse exceptionResponse = new ExceptionResponse();

        exceptionResponse.setSuccess(false);
        exceptionResponse.setErrorMessage(exception.getMessage());

        return exceptionResponse;
    }

}

