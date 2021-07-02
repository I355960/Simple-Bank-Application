package com.finalproject.bank.Exception;


import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.xml.ws.Response;
import java.util.NoSuchElementException;

@ControllerAdvice
public class bankExceptionControllerHandle {

    @ExceptionHandler(bankExceptionController.class)
    public ResponseEntity<String> handleBankException(bankExceptionController bankexceptioncontroller)
    {
        return new ResponseEntity<String>("Minimum balance are not maintained", HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleBankWithNoSuchElementException(NoSuchElementException noSuchElementException)
    {
        return new ResponseEntity<String>("No such element are not found", HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<String> handleBankWithEmptyResultDataAccessException(EmptyResultDataAccessException emptyResultDataAccessException)
    {
        return new ResponseEntity<String>("Empty record", HttpStatus.NOT_FOUND);
    }


}
