package com.security.mssecurity.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.Forbidden;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    
    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<Object> handleExceptionException(Exception ex, WebRequest request) {
        return new ResponseEntity<Object>(new ErrorMessage(new Date(), ex.getMessage()), new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(value = {Forbidden.class})
    public ResponseEntity<Object> handleExceptionForbidden(Forbidden ex, WebRequest request) {
        return new ResponseEntity<Object>(new ErrorMessage(new Date(), ex.getMessage()), new HttpHeaders(), HttpStatus.FORBIDDEN);
    }
}

//@ExceptionHandler(value = {  Exception.class }) 
//protected ResponseEntity<Object> handleConflict(
//RuntimeException ex, WebRequest request) {
//  String bodyOfResponse = "Ocorreu um erro interno no servidor!";
//  return handleExceptionInternal(ex, bodyOfResponse, 
//    new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
//}


