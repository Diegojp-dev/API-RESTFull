package com.api.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class RequireObjectIsNullException extends RuntimeException{

    public RequireObjectIsNullException(String msg){
        super(msg);
    }

    public RequireObjectIsNullException(){
        super("It is not allowes to persist a null object");
    }

}
