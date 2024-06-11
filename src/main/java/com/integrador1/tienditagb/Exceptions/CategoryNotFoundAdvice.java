package com.integrador1.tienditagb.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CategoryNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String,String> exceptionHandler(CategoryNotFoundException exception){
        Map<String,String> errorMap = new HashMap<>();
        errorMap.put("Mensaje de error",exception.getMessage());
        return errorMap;
    }
}