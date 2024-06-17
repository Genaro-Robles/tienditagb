package com.integrador1.tienditagb.Exceptions;

public class BuysNotFoundException extends RuntimeException{
    public BuysNotFoundException(int id){
        super("No se encontro una compra con el id: " + id);
    }
}
