package com.herokuapp.favsongslist.exception;

public class SongNotFoundException extends RuntimeException {
    public SongNotFoundException (String message) {
        super(message);
    }
}
