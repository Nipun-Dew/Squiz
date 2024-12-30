package com.example.squiz.services;

import com.example.squiz.dtos.QuizRequest;
import com.example.squiz.dtos.QuizResponse;
import com.example.squiz.dtos.info.QuizInfoResponse;
import com.example.squiz.dtos.info.QuizQuestionInfoResponse;
import com.example.squiz.entities.QuizEB;
import com.example.squiz.repos.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Long.parseLong;

@Service
public class QuizService {
    private final QuizRepository quizRepository;

    @Autowired
    public QuizService(QuizRepository quizRepository) {
        this.quizRepository = quizRepository;
    }

    public ResponseEntity<QuizInfoResponse> getQuiz(String id) {
        try {
            Optional<QuizEB> optionalQuiz = quizRepository.findQuizById(parseLong(id));
            QuizInfoResponse quizInfoResponse = new QuizInfoResponse();

            return optionalQuiz.map(quiz -> ResponseEntity.ok(quizInfoResponse.createQuizInfoResponse(quiz)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(quizInfoResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new QuizInfoResponse());
        }
    }

    @Transactional
    public ResponseEntity<Integer> createNewQuiz(QuizRequest quizRequest, String username) {
        try {
            QuizEB savedQuiz = quizRepository.save(quizRequest.createQuizEntity(username));
            String identifier = generateQuizIdentifier(username, savedQuiz.getId().toString());
            savedQuiz.setIdentifier(identifier);
            QuizEB updatedQuiz = quizRepository.save(savedQuiz);

            return ResponseEntity.ok(updatedQuiz.getId().intValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-1);
        }
    }

    public ResponseEntity<QuizResponse> findQuizByIdentifier(String identifier) {
        try {
            Optional<QuizEB> optionalQuiz = quizRepository.findQuizByIdentifier(identifier);
            QuizResponse quizResponse = new QuizResponse();

            return optionalQuiz.map(quiz -> ResponseEntity.ok(quizResponse.createQuizResponse(quiz)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(quizResponse));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new QuizResponse());
        }
    }

    public ResponseEntity<QuizQuestionInfoResponse> findQuestionsByQuiz(String quizId) {
        try {
            Optional<QuizEB> optionalQuiz = quizRepository.findQuizById(parseLong(quizId));
            QuizQuestionInfoResponse quizResponse = new QuizQuestionInfoResponse();

            return optionalQuiz.map(quiz -> ResponseEntity.ok(quizResponse.createQuizResponse(quiz)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(quizResponse));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new QuizQuestionInfoResponse());
        }
    }

    public ResponseEntity<Integer> changeQuizState(String quizId, String newState, String username) {
        try {
            Optional<QuizEB> optionalQuiz = quizRepository.findQuizById(parseLong(quizId));

            if (optionalQuiz.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(-1);
            }

            QuizEB quiz = optionalQuiz.get();
            if (!quiz.getCreatorId().equals(username)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(-1);
            }

            quiz.setState(newState);
            QuizEB updatedQuiz = quizRepository.save(quiz);

            return ResponseEntity.ok(updatedQuiz.getId().intValue());
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

    private String generateQuizIdentifier(String username, String quizId) {
        Instant now = Instant.now();
        long randTimestamp = now.getEpochSecond();
        return username + "@" + quizId + randTimestamp;
    }
}
