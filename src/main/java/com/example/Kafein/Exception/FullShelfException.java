package com.example.Kafein.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class FullShelfException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public FullShelfException(String message) {
        super(message);
    }
}
