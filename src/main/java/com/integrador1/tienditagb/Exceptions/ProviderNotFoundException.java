package com.integrador1.tienditagb.Exceptions;

public class ProviderNotFoundException extends RuntimeException{
    public ProviderNotFoundException(int id){
        super("No se encontro un proveedor con el id: "+ id);
    }
}
