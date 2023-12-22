package org.felipecpdev.redisspringdemo.exceptions;

public class EmployeeExceptionNotFound extends RuntimeException {

    public EmployeeExceptionNotFound(String message) {
        super(message);
    }
}
