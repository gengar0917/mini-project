package com.example.miniproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BasicResponseDto<T> {

    private boolean success;
    private String message;
    private T data;

    public static <T> BasicResponseDto<T> setSuccess(String message, T data) {
        return new BasicResponseDto<>(true, message, data);
    }

    public static <T> BasicResponseDto<T> setFailed(String message) {
        return new BasicResponseDto<>(false, message, null);
    }
}
