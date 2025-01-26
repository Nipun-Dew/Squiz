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
    List<QuestionsEB> findQuestionsByQuiz_Id(Long quizId);
}
