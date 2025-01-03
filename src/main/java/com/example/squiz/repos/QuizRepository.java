package com.example.squiz.repos;

import com.example.squiz.entities.QuizEB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<QuizEB, Long> {
    @Query("SELECT q FROM QuizEB q " +
            "LEFT JOIN FETCH q.questions " +
            "LEFT JOIN FETCH q.sessions WHERE q.creatorId = :creatorId")
    List<QuizEB> findQuizForCreator(@Param("creatorId") String creatorId);

    @Query("SELECT q FROM QuizEB q LEFT JOIN FETCH q.questions WHERE q.id = :quizId")
    Optional<QuizEB> findQuizById(@Param("quizId") Long quizId);

    @Query("SELECT q FROM QuizEB q WHERE q.identifier = :identifier")
    Optional<QuizEB> findQuizByIdentifier(@Param("identifier") String identifier);
}
