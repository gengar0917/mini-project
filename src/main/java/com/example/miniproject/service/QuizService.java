package com.example.miniproject.service;

import com.example.miniproject.dto.MsgResponseDto;
import com.example.miniproject.dto.QuizRequestDto;
import com.example.miniproject.dto.QuizResponseDto;
import com.example.miniproject.entity.Quiz;
import com.example.miniproject.entity.SolvedQuiz;
import com.example.miniproject.entity.User;
import com.example.miniproject.repository.QuizRepository;
import com.example.miniproject.repository.SolvedQuizRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final SolvedQuizRepository solvedQuizRepository;


    // 퀴즈 등록
    @Transactional
    public Long register(QuizRequestDto quizRequestDto, User user) {
        Quiz quiz = new Quiz(quizRequestDto, user.getUserId());
        Quiz savedQuiz = quizRepository.save(quiz);
        return savedQuiz.getId();
    }

    // 개별 퀴즈 조회
    public QuizResponseDto findById(Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 퀴즈가 없습니다."));
        return new QuizResponseDto(quiz);
    }

    // 전체 퀴즈 조회
    @Transactional(readOnly = true)
    public List<QuizResponseDto> findAll() {
        List<Quiz> quizzes = quizRepository.findAll();
        return quizzes.stream().map(QuizResponseDto::new).collect(Collectors.toList());
    }

    // 해결한 문제 조회
    public List<SolvedQuiz> SolvedListByUser(Long id) {;
        return solvedQuizRepository.findByUser(id);
    }

    // 문제 해결하는 API


    // 퀴즈 게시물 수정
    @Transactional
    public QuizResponseDto update(Long id, QuizRequestDto quizRequestDto, User user) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 퀴즈가 없습니다.")
        );

        if(!StringUtils.equals(quiz.getUserId(), user.getUserId())) {
            throw new IllegalArgumentException("회원을 찾을 수 없습니다.");
        } else {
            quiz.update(quizRequestDto);
            return new QuizResponseDto(quiz);
        }
    }

    // 퀴즈 게시물 삭제
    @Transactional
    public MsgResponseDto deleteAll(Long id, User user) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 퀴즈가 없습니다.")
        );
        if(!StringUtils.equals(quiz.getUserId(), user.getUserId())) {
            return new MsgResponseDto("아이디가 같지 않습니다.");
        } else {
            quizRepository.delete(quiz);
            return new MsgResponseDto("퀴즈 삭제 성공");
        }
    }
}
