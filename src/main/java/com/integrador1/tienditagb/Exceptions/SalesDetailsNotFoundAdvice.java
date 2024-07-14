package com.integrador1.tienditagb.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class SalesDetailsNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(SalesNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> exceptionHandler(SalesNotFoundException exception){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Mensaje de error",exception.getMessage());
        return errorMap;
    }
}