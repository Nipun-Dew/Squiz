package com.example.squiz.services;

import com.example.squiz.dtos.QuizRequest;
import com.example.squiz.dtos.QuizResponse;
import com.example.squiz.dtos.info.QuizInfoResponse;
import com.example.squiz.entities.QuizEB;
import com.example.squiz.repos.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Long.parseLong;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    private final QuizInfoResponse quizInfoResponse;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
        this.quizInfoResponse = new QuizInfoResponse();
    }

    public ResponseEntity<QuizInfoResponse> getQuiz(String id) {
        try {
            Optional<QuizEB> optionalQuiz = quizRepository.findQuizById(parseLong(id));

            return optionalQuiz.map(quiz -> ResponseEntity.ok(quizInfoResponse.createQuizInfoResponse(quiz)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(quizInfoResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(quizInfoResponse);
        }
    }

    public ResponseEntity<Integer> createNewQuiz(QuizRequest quizRequest, String username) {
        try {
            QuizEB savedQuiz = quizRepository.save(quizRequest.createQuizEntity(username));
            return ResponseEntity.ok(savedQuiz.getId().intValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-1);
        }
    }

    public ResponseEntity<List<QuizResponse>> findQuizzesForUser(String username) {
        try {
            List<QuizEB> quizzes = quizRepository.findQuizForCreator(username);
            List<QuizResponse> quizResponses = quizzes.stream()
                    .map(result -> new QuizResponse().createQuizResponse(result))
                    .toList();
            return ResponseEntity.ok(quizResponses);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        }
    }
}
