package com.example.miniproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AmendRequestDto {
    private String title;
    private String content;
}
