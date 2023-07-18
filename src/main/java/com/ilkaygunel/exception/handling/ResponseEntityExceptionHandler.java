package com.ilkaygunel.exception.handling;

import com.ilkaygunel.exception.customexceptions.NotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ResponseEntityExceptionHandler extends org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<Object> handleNotFound(NotFoundException notFoundException, WebRequest webRequest) {
        return handleExceptionInternal(notFoundException, notFoundException.getMessage(), new HttpHeaders(), HttpStatus.NOT_FOUND, webRequest);
    }
}
