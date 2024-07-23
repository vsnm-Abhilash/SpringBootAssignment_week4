package com.abhilash.sb.assignment_w4.advices;

import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiError {
    private String error;
    private LocalDateTime timeStamp;
    private HttpStatus status;

}
