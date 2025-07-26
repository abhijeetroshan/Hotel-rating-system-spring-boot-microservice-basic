package com.example.user.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException(String message)
    {
        super(message);
        System.out.println("ResourceNotFoundException Constructor Called");
    }

    public ResourceNotFoundException()
    {
        super("Resource not Found in the database !!!!");
        System.out.println("ResourceNotFoundException Constructor Called");
    }
}
