package com.example.miniproject.dto;

import com.example.miniproject.entity.Quiz;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class QuizRequestDto {
    private String title;
    private String content;
    private String correct;
    private String incorrect1;
    private String incorrect2;
    private String incorrect3;
    private long userId;
}
