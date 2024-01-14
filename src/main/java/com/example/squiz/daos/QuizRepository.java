package com.example.squiz.daos;

import com.example.squiz.entities.QuizEB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuizRepository extends JpaRepository<QuizEB, Long> {
    @Query("SELECT q FROM QuizEB q LEFT JOIN FETCH q.questions LEFT JOIN FETCH q.answerSets WHERE q.creatorId = :creatorId")
    List<QuizEB> findQuizForCreator(@Param("creatorId") Integer creatorId);
}
