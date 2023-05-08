package com.example.miniproject.controller;


import com.example.miniproject.dto.*;
import com.example.miniproject.entity.SolvedQuiz;
import com.example.miniproject.security.UserDetailsImpl;
import com.example.miniproject.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quiz")
public class QuizController {

    private final QuizService quizService;

    // 퀴즈 게시글 등록
    @PostMapping("/register")
    public BasicResponseDto<?> register(@RequestBody QuizRequestDto quizRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return quizService.register(quizRequestDto, userDetails.getUser());
    }


    // 전체 퀴즈 리스트
    @GetMapping
    public BasicResponseDto<List<QuizResponseDto>> findAll() {
        return quizService.findAll();
    }

    // 퀴즈 게시글 조회
    @GetMapping("/{quiz_id}")
    public BasicResponseDto<SolvingQuizResponseDto> findById(@PathVariable Long quiz_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return quizService.findById(quiz_id, userDetails.getUser());
    }

    // 마이페이지 (내가 해결한 문제들 조회)
    @GetMapping("/solved/{id}")
    public List<SolvedQuiz> solvedQuizByUser(@PathVariable(name = "id") Long user_id) {
        return quizService.SolvedListByUser(user_id);
    }

    // 문제 해결----> 동현님 방식
//    @PostMapping("/{id}/solved")
//    public ResponseEntity<Void> quizSolvedComplete(@PathVariable Long quizId, Principal principal) {
//        long user_id = Long.parseLong(principal.getName());
////        quizService.solvingQuiz(user_id, quizId);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
//                .replacePath("/quiz/{id}")
//                .buildAndExpand(quizId)
//                .toUri();
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).location(location).build();
//    }

//    // 문제 해결
//    @PostMapping("/solved/{quiz_id}")
//    public String quizSolvedComplete(@PathVariable Long quiz_id, @RequestBody AnswerRequestDto answerRequestDto, Authentication authentication) {
//        User user = (User) authentication.getPrincipal();
//        return quizService.solvingQuiz(quiz_id, answerRequestDto, user);
//    }


    // 퀴즈 게시글 수정하기
    @PutMapping("/{quiz_id}")
    public BasicResponseDto<?> update(@PathVariable Long quiz_id, @RequestBody AmendRequestDto amendRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return quizService.update(quiz_id, amendRequestDto, userDetails.getUser());
    }


    // 퀴즈 게시글 삭제하기
    @DeleteMapping("/{quiz_id}")
    public BasicResponseDto<?> deleteAll(@PathVariable Long quiz_id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return quizService.deleteAll(quiz_id, userDetails.getUser());
    }

    // 퀴즈 풀기
    @PostMapping("/{quiz_id}/solving")
    public BasicResponseDto<?> solvingQuiz(@PathVariable Long quiz_id, @RequestBody AnswerRequestDto answerRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
       return quizService.solvingQuiz(quiz_id, answerRequestDto, userDetails.getUser());
    }

}
