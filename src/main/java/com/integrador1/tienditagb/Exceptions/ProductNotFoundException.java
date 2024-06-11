package com.integrador1.tienditagb.Exceptions;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(int id){
        super("No se encontró un producto con el id "+id);
    }
}
