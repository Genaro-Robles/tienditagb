package com.integrador1.tienditagb.Exceptions;

public class SalesNotFoundException extends RuntimeException{
    public SalesNotFoundException(int id){
        super("No se encontró una venta con el id "+id);
    }
}
