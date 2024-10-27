package com.mockproject.javaGroup3.exception;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String entityName, int id) {
        super("Could not find " + entityName + " with id " + id);
    }
}
