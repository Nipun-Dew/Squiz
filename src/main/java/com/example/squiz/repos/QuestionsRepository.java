package com.example.squiz.repos;

import com.example.squiz.entities.QuestionsEB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionsRepository extends JpaRepository<QuestionsEB, Long> {
    @Query("SELECT q FROM QuestionsEB q " +
            "LEFT JOIN FETCH q.choices " +
            "LEFT JOIN FETCH q.answers " +
            "LEFT JOIN FETCH q.quiz WHERE q.quiz.id = :quizId")
    List<QuestionsEB> findQuestionsByQuiz(@Param("quizId") Long quizId);

    @Query("SELECT q FROM QuestionsEB q " +
            "LEFT JOIN FETCH q.choices " +
            "WHERE q.id = :questionId " +
            "ORDER BY q.id")
    Optional<QuestionsEB> findByQuestionId(@Param("questionId") Long questionId);
}
