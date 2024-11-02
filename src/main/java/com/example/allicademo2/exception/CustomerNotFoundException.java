package com.example.allicademo2.exception;

public class CustomerNotFoundException extends Exception {

    public CustomerNotFoundException(Integer id) {
        super("No customers found with id: " + id);
    }
}
