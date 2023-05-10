package com.example.miniproject.dto;

import com.example.miniproject.entity.Quiz;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuizResponseDto {
    private Long id;
    private String title;
    private String content;
    private String correct;
    private String incorrect1;
    private String incorrect2;
    private String incorrect3;
    private String userId;
    private boolean solved;

    public QuizResponseDto(Quiz quiz, boolean solved) {
        this.id = quiz.getId();
        this.userId = quiz.getUserId();
        this.title = quiz.getTitle();
        this.content = quiz.getContent();
        this.correct = quiz.getCorrect();
        this.incorrect1 = quiz.getIncorrect1();
        this.incorrect2 = quiz.getIncorrect2();
        this.incorrect3 = quiz.getIncorrect3();
        this.solved = solved;
    }
}
