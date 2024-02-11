package com.example.squiz.services;

import com.example.squiz.dtos.AnswersRequest;
import com.example.squiz.dtos.AnswersResponse;
import com.example.squiz.entities.AnswerSetsEB;
import com.example.squiz.entities.AnswersEB;
import com.example.squiz.entities.ChoicesEB;
import com.example.squiz.entities.QuestionsEB;
import com.example.squiz.repos.AnswerSetRepository;
import com.example.squiz.repos.AnswersRepository;
import com.example.squiz.repos.ChoiceRepository;
import com.example.squiz.repos.QuestionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class AnswersService {
    private final AnswersRepository answersRepository;
    private final QuestionsRepository questionsRepository;
    private final ChoiceRepository choiceRepository;
    private final AnswerSetRepository answerSetRepository;

    private final AnswersResponse answerResponse;

    @Autowired
    public AnswersService(AnswersRepository answersRepository,
                          QuestionsRepository questionsRepository,
                          ChoiceRepository choiceRepository,
                          AnswerSetRepository answerSetRepository) {
        this.answersRepository = answersRepository;
        this.questionsRepository = questionsRepository;
        this.choiceRepository = choiceRepository;
        this.answerSetRepository = answerSetRepository;
        this.answerResponse = new AnswersResponse();
    }

    public ResponseEntity<AnswersResponse> getAnswer(String id) {
        try {
            Optional<AnswersEB> optionalAnswer = answersRepository.findById(Long.parseLong(id));

            return optionalAnswer
                    .map(answer -> ResponseEntity.ok(answerResponse.createAnswerResponse(answer)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(answerResponse));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(answerResponse);
        }
    }

    public ResponseEntity<Integer> createNewAnswer(AnswersRequest answerRequest) {
        try {
            QuestionsEB questionEntity = questionsRepository.findById(answerRequest.getQuestionId().longValue())
                    .orElseThrow();
            ChoicesEB choiceEntity = choiceRepository.findById(answerRequest.getChoiceId().longValue())
                    .orElseThrow();
            AnswerSetsEB answerSetEntity = answerSetRepository.findById(answerRequest.getAnswerSetId().longValue())
                    .orElseThrow();
            List<ChoicesEB> choices = choiceRepository.getChoicesForQuestion(questionEntity.getId());

            String correctAnswer = Objects.requireNonNull(choices.stream()
                            .filter(ChoicesEB::getCorrectAnswer)
                            .findAny()
                            .orElse(null))
                    .getChoiceText();

            AnswersEB savedAnswer = answersRepository.save(answerRequest.createAnswerEntity(questionEntity,
                    choiceEntity,
                    answerSetEntity,
                    choiceEntity.getCorrectAnswer(),
                    correctAnswer));

            return ResponseEntity.ok(savedAnswer.getId().intValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-1);
        }
    }

    public ResponseEntity<List<AnswersResponse>> getAnswersForAnswerSet(String answerSetId) {
        try {
            List<AnswersEB> results = answersRepository.getAnswersForAnswerSet(Long.parseLong(answerSetId));
            List<AnswersResponse> answersResponses = results.stream()
                    .map(result -> new AnswersResponse().createAnswerResponse(result))
                    .toList();
            return ResponseEntity.ok(answersResponses);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ArrayList<>());
        }
    }
}
