package com.security.mssecurity.exceptions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;


// Desativada não funciona!!!
//@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle
      (HttpServletRequest request, HttpServletResponse response, AccessDeniedException ex) 
      throws IOException, ServletException {
        response.sendRedirect("/api/index");
    }

   
}