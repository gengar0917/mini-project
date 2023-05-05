package com.example.miniproject.controller;


import com.example.miniproject.dto.MsgResponseDto;
import com.example.miniproject.dto.QuizRequestDto;
import com.example.miniproject.dto.QuizResponseDto;
import com.example.miniproject.entity.SolvedQuiz;
import com.example.miniproject.entity.User;
import com.example.miniproject.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    // 퀴즈 게시글 등록
    @PostMapping("/register")
    public long register(@RequestBody QuizRequestDto quizRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
//        String username = authentication.getName();
        return quizService.register(quizRequestDto, userDetails.getUser());
    }


    // 전체 퀴즈 리스트
    @GetMapping
    public List<QuizResponseDto> findAll() {
        return quizService.findAll();
    }

    // 퀴즈 게시글 조회
    @GetMapping("/{id}")
    public QuizResponseDto findById(@PathVariable Long id) {
        return quizService.findById(id);
    }

    // 해결한 퀴즈 조회
    @GetMapping("/solved/{userId}")
    public List<SolvedQuiz> solvedQuizByUser(@PathVariable Long userId) {
        List<SolvedQuiz> solvedQuizList = quizService.SolvedListByUser(userId);
        return solvedQuizList;
    }

    // 문제 해결
//    @GetMapping("/{id}/solved")
//    public void quizSolvedComplete(@PathVariable

    // 퀴즈 게시글 수정하기
    @PutMapping("/{quiz_id}")
    public QuizResponseDto update(@PathVariable Long id, @RequestBody QuizRequestDto quizRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return quizService.update(id, quizRequestDto, userDetails.getUser());
    }


    // 퀴즈 게시글 삭제하기
    @DeleteMapping("/{quiz_id}")
    public MsgResponseDto deleteAll(@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return quizService.deleteAll(id, userDetails.getUser());
    }



}
