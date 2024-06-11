package com.integrador1.tienditagb.Exceptions;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(int id){
        super("No se encontró un usuario con el id "+id);
    }
}
