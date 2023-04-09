package com.example.Kafein.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class AssociatedDataException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    public AssociatedDataException(String message) {
        super(message);}
}
