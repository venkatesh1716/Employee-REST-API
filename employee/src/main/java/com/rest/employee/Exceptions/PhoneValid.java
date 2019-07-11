package com.rest.employee.Exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class PhoneValid  extends RuntimeException{

    public PhoneValid(String message) {
        super(message);
    }
}
