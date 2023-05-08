package com.example.miniproject.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class SolvingQuizResponseDto {
    private Long id;
    private String title;
    private String content;
    List<String> answerList;
    private String userId;
    private boolean solved;

}
