package com.integrador1.tienditagb.Exceptions;

public class SalesDetailsNotFoundException extends RuntimeException{
    public SalesDetailsNotFoundException(int id){
        super("No se encontró una venta con el id "+id);
    }
}
