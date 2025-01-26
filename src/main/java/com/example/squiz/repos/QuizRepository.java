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
    List<QuizEB> findQuizByCreatorId(String creatorId);

    Optional<QuizEB> findQuizById(Long id);

    Optional<QuizEB> findQuizByIdentifier(String identifier);
}
