package com.pcornejo.bciJavaTest.exception;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlers {

    private Logger log = LoggerFactory.getLogger(ExceptionHandlers.class);

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UserNotFoundException.class)
    @ResponseBody
    public ErrorResponse handleThrowable(final UserNotFoundException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmailAlreadyExistException.class)
    @ResponseBody
    public ErrorResponse handleThrowable(final EmailAlreadyExistException ex) {
        return new ErrorResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ErrorResponse handleConstraint(final HttpMessageNotReadableException ex) {
        log.error("HttpMessageNotReadableException Error", ex);
        if(ex.getCause() instanceof InvalidFormatException) {
            return new ErrorResponse("El valor " + ((InvalidFormatException) ex.getCause()).getValue() + " debe ser de tipo " + ((InvalidFormatException) ex.getCause()).getTargetType());
        }
        if(ex.getCause() instanceof JsonMappingException) {
            return new ErrorResponse("Error JSON mal formado");
        }
        return new ErrorResponse(ex.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ErrorResponse handleConstraint(final MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException Error", ex);
        return new ErrorResponse(ex.getBindingResult().getFieldErrors().stream().map(
                (err) -> err.getField() + " " + err.getDefaultMessage())
                .collect(Collectors.joining("; ")));
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    public ErrorResponse handleThrowable(final Throwable ex) {
        log.error("Throwable Error", ex);
        return new ErrorResponse("Error inesperado del servicio");
    }
}
