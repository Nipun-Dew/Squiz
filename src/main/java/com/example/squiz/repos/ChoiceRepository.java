package com.example.squiz.repos;

import com.example.squiz.entities.ChoicesEB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChoiceRepository extends JpaRepository<ChoicesEB, Long> {
    List<ChoicesEB> findChoicesByQuestions_Id(Long questionsId);
}
