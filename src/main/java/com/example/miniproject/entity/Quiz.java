package com.example.miniproject.entity;

import com.example.miniproject.dto.AmendRequestDto;

import com.example.miniproject.dto.QuizRequestDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String content;
    @Column(nullable = false)
    private String correct;
    @Column
    private String incorrect1;
    @Column
    private String incorrect2;
    @Column
    private String incorrect3;
    @Column
    private String userId;
    @Column
    private Long solvedQuizCnt;
    @OneToMany(mappedBy = "quiz", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonBackReference
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.REMOVE)
    private List<SolvedQuiz> solvedQuiz = new ArrayList<>();

    // 퀴즈 게시글 등록
    public Quiz(QuizRequestDto quizRequestDto, String userId) {
        this.title = quizRequestDto.getTitle();
        this.content = quizRequestDto.getContent();
        this.userId = userId;
        this.correct = quizRequestDto.getCorrect();
        this.incorrect1 = quizRequestDto.getIncorrect1();
        this.incorrect2 = quizRequestDto.getIncorrect2();
        this.incorrect3 = quizRequestDto.getIncorrect3();
    }

    // 퀴즈 게시글 수정
    public void update(AmendRequestDto amendRequestDto) {
        this.title = amendRequestDto.getTitle();
        this.content = amendRequestDto.getContent();
    }

    // 연관관계 편의 메서드
    public void addComment(Comment comment) {
        commentList.add(comment);
        comment.setQuiz(this);
    }

    public void addSolvedQuiz(SolvedQuiz solvedQuiz) {
        this.solvedQuiz.add(solvedQuiz);
        solvedQuiz.setQuiz(this);
    }
}
