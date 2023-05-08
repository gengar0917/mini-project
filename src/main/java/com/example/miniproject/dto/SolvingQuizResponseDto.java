package com.example.miniproject.dto;

import com.example.miniproject.entity.Quiz;
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

    public SolvingQuizResponseDto(Quiz quiz, List<String> answerList, boolean solved) {
        this.id = quiz.getId();
        this.title = quiz.getTitle();
        this.content = quiz.getContent();
        this.answerList = answerList;
        this.userId = getUserId();
        this.solved = solved;
    }
}
