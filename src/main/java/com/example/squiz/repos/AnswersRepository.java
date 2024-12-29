package com.example.squiz.repos;

import com.example.squiz.entities.AnswersEB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswersRepository extends JpaRepository<AnswersEB, Long> {
    @Query("SELECT a FROM AnswersEB a " +
            "LEFT JOIN FETCH a.sessions " +
            "LEFT JOIN FETCH a.questions " +
            "LEFT JOIN FETCH a.choices WHERE a.sessions.id = :sessionId")
    List<AnswersEB> getAnswersForSession(@Param("sessionId") long sessionId);
}
