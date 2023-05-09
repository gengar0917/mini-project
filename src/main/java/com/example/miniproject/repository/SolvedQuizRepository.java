package com.example.miniproject.repository;

import com.example.miniproject.entity.SolvedQuiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SolvedQuizRepository extends JpaRepository<SolvedQuiz, Long> {
    @Query("Select sq from SolvedQuiz sq where sq.user.id = :id")
    List<SolvedQuiz> selectSolvedQuiz(@Param("id") Long id);

    @Query("select count (sq) from SolvedQuiz sq where sq.user.id = :id")
    Long countSolvedQuiz(@Param("id") Long id);

    @Query("select sq from SolvedQuiz sq where sq.quiz.id = :id and sq.user.id = :userId")
    SolvedQuiz findByUserIdAndQuizId(@Param("userId") Long userId, @Param("id") Long id);

}
