package com.abhilash.sb.assignment_w4.advices;

import com.abhilash.sb.assignment_w4.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFound(ResourceNotFoundException exception){
        ApiError apiError=ApiError.builder()
                .timeStamp(LocalDateTime.now())
                .error(exception.getLocalizedMessage())
                .status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }
}
