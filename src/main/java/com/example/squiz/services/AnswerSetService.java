package com.example.squiz.services;

import com.example.squiz.dtos.AnswerSetRequest;
import com.example.squiz.dtos.AnswerSetResponse;
import com.example.squiz.entities.AnswerSetsEB;
import com.example.squiz.entities.AnswersEB;
import com.example.squiz.entities.QuizEB;
import com.example.squiz.repos.AnswerSetRepository;
import com.example.squiz.repos.AnswersRepository;
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
    private final AnswersRepository answersRepository;

    private final AnswerSetResponse answerSetResponse;

    @Autowired
    public AnswerSetService(AnswerSetRepository answerSetRepository,
                            QuizRepository quizRepository, AnswersRepository answersRepository) {
        this.answerSetRepository = answerSetRepository;
        this.quizRepository = quizRepository;
        this.answersRepository = answersRepository;
        this.answerSetResponse = new AnswerSetResponse();
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

    public ResponseEntity<Integer> createNewAnswerSet(AnswerSetRequest answerSetRequest, String username) {
        try {
            QuizEB quizEntity = quizRepository.findById(answerSetRequest.getQuizId().longValue())
                    .orElseThrow();
            AnswerSetsEB savedAnswerSet = answerSetRepository
                    .save(answerSetRequest.createAnswerSetEntity(quizEntity, username));
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
                    .map(result -> new AnswerSetResponse().createAnswerSetResponse(result))
                    .toList();
            return ResponseEntity.ok(answerSetResponses);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        }
    }

    public ResponseEntity<Integer> getPointsForAnswerSet(String answerSetId) {
        try {
            List<AnswersEB> results = answersRepository.getAnswersForAnswerSet(Long.parseLong(answerSetId));
            Integer points = results.stream().filter(AnswersEB::getIsCorrectAnswer).toList().size();

            return ResponseEntity.ok(points);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-1);
        }
    }
}
