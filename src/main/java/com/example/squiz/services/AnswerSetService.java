package com.example.squiz.services;

import com.example.squiz.dtos.AnswerSetRequest;
import com.example.squiz.dtos.AnswerSetResponse;
import com.example.squiz.dtos.ChoiceRequest;
import com.example.squiz.dtos.ChoiceResponse;
import com.example.squiz.entities.AnswerSetsEB;
import com.example.squiz.entities.ChoicesEB;
import com.example.squiz.entities.QuestionsEB;
import com.example.squiz.entities.QuizEB;
import com.example.squiz.repos.AnswerSetRepository;
import com.example.squiz.repos.QuestionsRepository;
import com.example.squiz.repos.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AnswerSetService {
    private final AnswerSetRepository answerSetRepository;
    private final QuizRepository quizRepository;
    private final QuestionsRepository questionsRepository;

    private final AnswerSetResponse answerSetResponse;

    @Autowired
    public AnswerSetService(AnswerSetRepository answerSetRepository,
                            QuizRepository quizRepository,
                            QuestionsRepository questionsRepository,
                            AnswerSetResponse answerSetResponse) {
        this.answerSetRepository = answerSetRepository;
        this.quizRepository = quizRepository;
        this.questionsRepository = questionsRepository;
        this.answerSetResponse = answerSetResponse;
    }

    public ResponseEntity<AnswerSetResponse> getAnswerSet(String id) {
        try {
            Optional<AnswerSetsEB> optionalAnswerSet = answerSetRepository.findById(Long.parseLong(id));

            return optionalAnswerSet
                    .map(answerSets -> ResponseEntity.ok(answerSetResponse.createAnswerSetResponse(answerSets)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(answerSetResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(answerSetResponse);
        }
    }

    public ResponseEntity<Integer> createNewAnswerSet(AnswerSetRequest answerSetRequest) {
        try {
            QuizEB quizEntity = quizRepository.findById(answerSetRequest.getQuizId().longValue())
                    .orElseThrow();
            AnswerSetsEB savedAnswerSet = answerSetRepository
                    .save(answerSetRequest.createAnswerSetEntity(quizEntity));
            return ResponseEntity.ok(savedAnswerSet.getId().intValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-1);
        }
    }

    public ResponseEntity<List<AnswerSetResponse>> getAnswerSetsForQuiz(String quizId) {
        try {
            List<AnswerSetsEB> results = answerSetRepository.getAnswerSetsForQuiz(Long.parseLong(quizId));
            List<AnswerSetResponse> answerSetResponses = results.stream()
                    .map(answerSetResponse::createAnswerSetResponse).toList();
            return ResponseEntity.ok(answerSetResponses);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        }
    }
}
