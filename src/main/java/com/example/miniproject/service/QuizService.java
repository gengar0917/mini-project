package com.example.miniproject.service;

import com.example.miniproject.dto.MsgResponseDto;
import com.example.miniproject.dto.QuizRequestDto;
import com.example.miniproject.dto.QuizResponseDto;
import com.example.miniproject.dto.SolvingQuizResponseDto;
import com.example.miniproject.entity.Quiz;
import com.example.miniproject.entity.SolvedQuiz;
import com.example.miniproject.entity.User;
import com.example.miniproject.repository.QuizRepository;
import com.example.miniproject.repository.SolvedQuizRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QuizService {

    private final QuizRepository quizRepository;
    private final SolvedQuizRepository solvedQuizRepository;


    // 퀴즈 등록
    @Transactional
    public QuizResponseDto register(QuizRequestDto quizRequestDto, User user) {
        Quiz quiz = new Quiz(quizRequestDto, user.getUserId());
        Quiz savedQuiz = quizRepository.save(quiz);
        return new QuizResponseDto(savedQuiz);
    }

    // 개별 퀴즈 조회
    @Transactional(readOnly = true)
    public SolvingQuizResponseDto findById(Long id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 퀴즈가 없습니다."));
        List<String> answerList = new ArrayList<>();
        answerList.add(quiz.getCorrect());
        if (quiz.getIncorrect1()!=null) {answerList.add(quiz.getIncorrect1());}
        if (quiz.getIncorrect2()!=null) {answerList.add(quiz.getIncorrect2());}
        if (quiz.getIncorrect3()!=null) {answerList.add(quiz.getIncorrect3());}
        Collections.shuffle(answerList);

        return new SolvingQuizResponseDto(quiz.getId(),quiz.getContent(), quiz.getTitle(), answerList, quiz.getUserId());
    }

    // 전체 퀴즈 조회
    @Transactional(readOnly = true)
    public List<QuizResponseDto> findAll() {
        List<Quiz> quizzes = quizRepository.findAll();
        return quizzes.stream().map(QuizResponseDto::new).collect(Collectors.toList());
    }

    // 해결한 문제 조회
    @Transactional(readOnly = true)
    public List<SolvedQuiz> SolvedListByUser(Long id) {
        return solvedQuizRepository.selectSolvedQuiz(id);
    }

    // 문제 해결하는 API
//    @Transactional
//    public MsgResponseDto solvingQuiz(long userId, long quizId, String answer, HttpServletResponse response) {
//    // userId, quizId 받고
//        Quiz quiz = quizRepository.findById(quizId).orElseThrow(()-> new ChangeSetPersister.NotFoundException("404 Quiz NOT FOUND"));
//        SolvedQuiz solvedQuiz = solvedQuizRepository.findByUserIdAndQuizId(userId, quizId);
//        // solvedQuiz.setSolved(true)를 위해서 정답을 form으로 입력받았을 때,
//        if (quiz.getCorrect().equals(answer)) {
//            // 정답과 equals면 setsolved(true)
//            if (!solvedQuiz.isSolved()) { // 이미 푼 문제 제외
//                        solvedQuiz.setSolved(true);
//                        solvedQuizRepository.save(solvedQuiz);
//                    }
//            response.sendRedirect("/quiz");
//        } else {
//
//        }
//        // redirect 문제리스트 화면
//        // !equals면 alert : 틀렸습니다.
//        // redirect 문제상세화면
//
//        //..............................................//
//        /*
//        solvedQuizRepository에 Query문을 적어서
//        true count를 세는 Query를 적어서
//
//         */
//
//    }


    // 퀴즈 게시물 수정
    @Transactional
    public QuizResponseDto update(Long id, QuizRequestDto quizRequestDto, User user) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(
                () -> new NullPointerException("해당 퀴즈가 없습니다.")
        );

        if(!StringUtils.equals(quiz.getId(), user.getId())) {
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
        if(!StringUtils.equals(quiz.getId(), user.getId())) {
            return new MsgResponseDto("아이디가 같지 않습니다.");
        } else {
            quizRepository.delete(quiz);
            return new MsgResponseDto("퀴즈 삭제 성공");
        }
    }
}
