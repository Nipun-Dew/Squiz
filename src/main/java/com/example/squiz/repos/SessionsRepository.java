package com.example.squiz.repos;

import com.example.squiz.entities.SessionsEB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SessionsRepository extends JpaRepository<SessionsEB, Long> {
    @Query("SELECT s FROM SessionsEB s " +
            "LEFT JOIN FETCH s.quiz " +
            "LEFT JOIN FETCH s.answers " +
            "WHERE s.quiz.id = :quizId AND s.userId = :userId")
    List<SessionsEB> getSessionsForQuizByUserId(@Param("quizId") long quizId, @Param("userId") String userId);

    List<SessionsEB> findSessionsByQuiz_Id(Long quizId);
}
