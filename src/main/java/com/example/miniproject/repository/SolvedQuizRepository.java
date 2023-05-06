package com.example.miniproject.repository;

import com.example.miniproject.entity.SolvedQuiz;
import com.example.miniproject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface SolvedQuizRepository extends JpaRepository<SolvedQuiz, Long> {
    @Query("Select sq from SolvedQuiz sq where sq.user.id = :id")
    List<SolvedQuiz> selectSolvedQuiz(long id);

    @Query("select count (sq) from SolvedQuiz sq where sq.quiz = :quizId and sq.solved = true")
    Long countsolvedquiz(@Param("quiz") Long quiz);

    SolvedQuiz findByUserIdAndQuizId(Long userId, Long quizId);

    List<SolvedQuiz> findUserByQuizId(@Param("quizId") Long quizId);

}
