package com.example.squiz.repos;

import com.example.squiz.entities.AnswerSetsEB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerSetRepository extends JpaRepository<AnswerSetsEB, Long> {
    @Query("SELECT a FROM AnswerSetsEB a " +
            "LEFT JOIN FETCH a.quiz " +
            "LEFT JOIN FETCH a.answers WHERE a.quiz.id = :quizId")
    List<AnswerSetsEB> getAnswerSetsForQuiz(@Param("quizId") long quizId);
}
