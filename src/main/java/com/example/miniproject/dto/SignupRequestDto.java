package com.example.miniproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    @NotBlank
    private String userId;

    @NotBlank
    private String password;
}
