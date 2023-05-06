package com.example.miniproject.entity;

import com.example.miniproject.entity.User;
import com.example.miniproject.dto.QuizRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
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

//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "user_id")
//    private User user;


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
    public void update(QuizRequestDto quizRequestDto) {
        this.title = quizRequestDto.getTitle();
        this.content = quizRequestDto.getContent();
        this.correct = quizRequestDto.getCorrect();
        this.incorrect1 = quizRequestDto.getIncorrect1();
        this.incorrect2 = quizRequestDto.getIncorrect2();
        this.incorrect3 = quizRequestDto.getIncorrect3();
    }
}
