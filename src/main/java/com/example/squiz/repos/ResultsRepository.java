package com.example.squiz.repos;

import com.example.squiz.entities.ResultsEB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultsRepository extends JpaRepository<ResultsEB, Long> {
    Optional<ResultsEB> getResultsBySession_Id(Long sessionId);
}
