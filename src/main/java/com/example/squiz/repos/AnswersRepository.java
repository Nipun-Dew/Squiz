package com.example.squiz.repos;

import com.example.squiz.entities.AnswersEB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswersRepository extends JpaRepository<AnswersEB, Long> {
    @Query("SELECT a FROM AnswersEB a " +
            "WHERE a.session.id = :sessionId AND a.question.id = :questionId")
    Optional<AnswersEB> findByQuestionIdAndSessionId(@Param("questionId") long questionId, @Param("sessionId") long sessionId);

    List<AnswersEB> findAnswersBySession_Id(Long sessionId);
}
