package com.integrador1.tienditagb.Exceptions;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(int id){
        super("No se encontró una categoría con el id "+id);
    }
}
