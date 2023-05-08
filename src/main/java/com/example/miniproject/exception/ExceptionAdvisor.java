package com.example.miniproject.exception;

import com.example.miniproject.dto.BasicResponseDto;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class ExceptionAdvisor {
    @ExceptionHandler(IllegalArgumentException.class)
    public BasicResponseDto<?> exceptionHandler(Exception exception) {
        String message = exception.getMessage();
        return BasicResponseDto.setFailed(message);
    }
}
