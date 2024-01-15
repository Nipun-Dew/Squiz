package com.example.squiz.repos;

import com.example.squiz.entities.ChoicesEB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoiceRepository extends JpaRepository<ChoicesEB, Long> {
    @Query("SELECT c FROM ChoicesEB c " +
            "LEFT JOIN FETCH c.questions " +
            "LEFT JOIN FETCH c.answers WHERE c.questions.id = :questionId")
    List<ChoicesEB> getChoicesForQuestion(@Param("questionId") long questionId);
}
