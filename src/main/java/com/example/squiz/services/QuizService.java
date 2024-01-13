package com.example.squiz.services;

import com.example.squiz.daos.QuizRepository;
import com.example.squiz.dtos.QuizRequest;
import com.example.squiz.dtos.QuizResponse;
import com.example.squiz.entities.QuizEB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.lang.Long.parseLong;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    private final QuizResponse quizResponse;

    @Autowired
    public QuizService(QuizRepository quizRepository, QuizResponse quizResponse) {
        this.quizRepository = quizRepository;
        this.quizResponse = quizResponse;
    }

    public ResponseEntity<QuizResponse> getQuiz(String id) {
        try {
            Optional<QuizEB> optionalQuiz = quizRepository.findById(parseLong(id));

            return optionalQuiz.map(quiz -> ResponseEntity.ok(quizResponse.createQuizResponse(quiz)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(quizResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(quizResponse);
        }
    }

    public ResponseEntity<Integer> createNewQuiz(QuizRequest quizRequest) {
        try {
            QuizEB savedQuiz = quizRepository.save(quizRequest.createQuizEntity());
            return ResponseEntity.ok(savedQuiz.getId().intValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-1);
        }
    }

    public ResponseEntity<List<QuizResponse>> findQuizForCreator(Integer creatorId) {
        try {
            List<QuizEB> quizzes = quizRepository.findQuizForCreator(creatorId);
            List<QuizResponse> quizResponses = quizzes.stream().map(quizResponse::createQuizResponse).toList();
            return ResponseEntity.ok(quizResponses);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        }
    }
}
