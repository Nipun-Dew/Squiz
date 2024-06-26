package com.example.squiz.services;

import com.example.squiz.dtos.QuestionsRequest;
import com.example.squiz.dtos.QuestionsResponse;
import com.example.squiz.entities.QuestionsEB;
import com.example.squiz.entities.QuizEB;
import com.example.squiz.repos.QuestionsRepository;
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
public class QuestionsService {
    private final QuestionsRepository questionsRepository;
    private final QuizRepository quizRepository;

    private final QuestionsResponse questionsResponse;

    @Autowired
    public QuestionsService(QuestionsRepository questionsRepository,
                            QuizRepository quizRepository) {
        this.questionsRepository = questionsRepository;
        this.quizRepository = quizRepository;
        this.questionsResponse = new QuestionsResponse();
    }

    public ResponseEntity<QuestionsResponse> getQuestion(String id) {
        try {
            Optional<QuestionsEB> optionalQuestion = questionsRepository.findById(parseLong(id));

            return optionalQuestion.map(quiz -> ResponseEntity.ok(questionsResponse.createQuestionsResponse(quiz)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(questionsResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(questionsResponse);
        }
    }

    public ResponseEntity<Integer> createNewQuestion(QuestionsRequest questionsRequest, String username) {
        try {
            QuestionsEB savedQuestion;
            QuizEB quizEntity = quizRepository.findById(questionsRequest.getQuizId().longValue()).orElseThrow();

            if(quizEntity.getCreatorId().equals(username)) {
                savedQuestion = questionsRepository.save(questionsRequest.createQuestionEntity(quizEntity));
            } else {
                throw new Exception("User is not the creator of the relevant Quiz");
            }
            return ResponseEntity.ok(savedQuestion.getId().intValue());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-1);
        }
    }

    public ResponseEntity<List<QuestionsResponse>> findQuestionsByQuiz(String quizId) {
        try {
            List<QuestionsEB> results = questionsRepository.findQuestionsByQuiz(Long.parseLong(quizId));
            List<QuestionsResponse> questionResponses = results.stream()
                    .map(result -> new QuestionsResponse().createQuestionsResponse(result))
                    .toList();
            return ResponseEntity.ok(questionResponses);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        }
    }
}
