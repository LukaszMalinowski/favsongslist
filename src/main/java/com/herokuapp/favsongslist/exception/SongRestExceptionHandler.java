package com.herokuapp.favsongslist.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class SongRestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<SongErrorResponse> handleException(SongNotFoundException ex) {
        SongErrorResponse response = new SongErrorResponse(HttpStatus.NOT_FOUND.value(), ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<SongErrorResponse> handleException(Exception ex) {
        SongErrorResponse response = new SongErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
