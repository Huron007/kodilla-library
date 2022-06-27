package com.example.kodillalibrary.controller;

import com.example.kodillalibrary.exception.BookNotFoundException;
import com.example.kodillalibrary.exception.CopyNotFoundException;
import com.example.kodillalibrary.exception.ReaderNotFoundException;
import com.example.kodillalibrary.exception.RentalNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException bookNotFoundException){
        return new ResponseEntity<>("Book with given ID does not exist.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CopyNotFoundException.class)
    public ResponseEntity<Object> handleCopyNotFoundException(CopyNotFoundException copyNotFoundException){
        return new ResponseEntity<>("Copy with given ID does not exist.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ReaderNotFoundException.class)
    public ResponseEntity<Object> handleReaderNotFoundException(ReaderNotFoundException readerNotFoundException){
        return new ResponseEntity<>("Reader with given ID does not exist.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RentalNotFoundException.class)
    public ResponseEntity<Object> handleRentalNotFoundException(RentalNotFoundException rentalNotFoundException){
        return new ResponseEntity<>("Rental with given ID does not exist.", HttpStatus.NOT_FOUND);
    }
}
