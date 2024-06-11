package com.integrador1.tienditagb.Exceptions;

public class RoleNotFoundException extends RuntimeException{
    public RoleNotFoundException(int id){
        super("No se encontró un rol con el id "+id);
    }
}
