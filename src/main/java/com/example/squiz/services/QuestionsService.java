package com.example.squiz.services;

import com.example.squiz.dtos.QuestionsRequest;
import com.example.squiz.dtos.QuestionsResponse;
import com.example.squiz.dtos.info.QuestionsInfoResponse;
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

    private final QuestionsInfoResponse questionsInfoResponse;

    @Autowired
    public QuestionsService(QuestionsRepository questionsRepository,
                            QuizRepository quizRepository) {
        this.questionsRepository = questionsRepository;
        this.quizRepository = quizRepository;
        this.questionsInfoResponse = new QuestionsInfoResponse();
    }

    public ResponseEntity<QuestionsInfoResponse> getQuestionInfo(String id) {
        try {
            Optional<QuestionsEB> optionalQuestionInfo = questionsRepository.findByQuestionId(parseLong(id));

            return optionalQuestionInfo.map(question -> ResponseEntity.ok(questionsInfoResponse.createQuestionInfoResponse(question)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(questionsInfoResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(questionsInfoResponse);
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

    public ResponseEntity<List<QuestionsInfoResponse>> findQuestionsByQuiz(String quizId) {
        try {
            List<QuestionsEB> results = questionsRepository.findQuestionsByQuiz(Long.parseLong(quizId));
            List<QuestionsInfoResponse> questionResponses = results.stream()
                    .map(result -> new QuestionsInfoResponse().createQuestionInfoResponse(result))
                    .toList();
            return ResponseEntity.ok(questionResponses);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        }
    }
}
