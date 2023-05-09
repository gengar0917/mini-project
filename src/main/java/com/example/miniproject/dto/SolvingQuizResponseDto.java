package com.example.miniproject.dto;

import com.example.miniproject.entity.Quiz;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@AllArgsConstructor
public class SolvingQuizResponseDto {
    private Long id;
    private String title;
    private String content;
    List<String> answerList;
    private String userId;
    private boolean solved;
    private List<CommentResponseDto> commentList;

    public SolvingQuizResponseDto(Quiz quiz, List<String> answerList, boolean solved) {
        this.id = quiz.getId();
        this.title = quiz.getTitle();
        this.content = quiz.getContent();
        this.answerList = answerList;
        this.userId = quiz.getUserId();
        this.solved = solved;
        this.commentList = quiz.getCommentList().stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }
}
