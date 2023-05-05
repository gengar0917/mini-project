package com.example.miniproject.repository;

import com.example.miniproject.entity.SolvedQuiz;
import com.example.miniproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolvedQuizRepository extends JpaRepository<SolvedQuiz, Long> {
    List<SolvedQuiz> findByUser(long id);

    SolvedQuiz findByUserIdAndQuizId(Long userId, Long quizId);

//    List<SolvedQuiz> findUserByQuizId(@Param("quizId") Long quizId);

}
