package com.example.squiz.services;

import com.example.squiz.dtos.AnswersRequest;
import com.example.squiz.dtos.AnswersResponse;
import com.example.squiz.entities.SessionsEB;
import com.example.squiz.entities.AnswersEB;
import com.example.squiz.entities.ChoicesEB;
import com.example.squiz.entities.QuestionsEB;
import com.example.squiz.exceptions.customExceptions.InternalServerErrorException;
import com.example.squiz.exceptions.customExceptions.NoContentException;
import com.example.squiz.exceptions.customExceptions.UnauthorizedException;
import com.example.squiz.repos.SessionsRepository;
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
    private final SessionsRepository sessionsRepository;

    @Autowired
    public AnswersService(AnswersRepository answersRepository,
                          QuestionsRepository questionsRepository,
                          ChoiceRepository choiceRepository,
                          SessionsRepository sessionsRepository) {
        this.answersRepository = answersRepository;
        this.questionsRepository = questionsRepository;
        this.choiceRepository = choiceRepository;
        this.sessionsRepository = sessionsRepository;
    }

    public ResponseEntity<AnswersResponse> getAnswer(String id) {
        try {
            Optional<AnswersEB> optionalAnswer = answersRepository.findById(Long.parseLong(id));

            return optionalAnswer
                    .map(answer -> ResponseEntity.ok(new AnswersResponse().createAnswerResponse(answer)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(new AnswersResponse()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AnswersResponse());
        }
    }

    public ResponseEntity<Integer> createNewAnswer(AnswersRequest answerRequest, String username) {
        try {
            AnswersEB savedAnswer;
            Optional<AnswersEB> optionalAnswer = answersRepository.findByQuestionIdAndSessionId(answerRequest.getQuestionId(),
                    answerRequest.getSessionId());

            // If an answer already provided for the question within the session, should update the answer instead of creating
            optionalAnswer.ifPresent(answersEB -> answerRequest.setId(answersEB.getId()));

            SessionsEB sessionEntity = sessionsRepository.findById(answerRequest.getSessionId())
                    .orElseThrow(() -> new NoContentException("Can not find a session to create an Answer"));

            String userId = sessionEntity.getUserId();

            // check whether the session user and answering user both same
            if (userId.equals(username)) {
                QuestionsEB questionEntity = questionsRepository.findById(answerRequest.getQuestionId())
                        .orElseThrow();

                ChoicesEB userGivenChoice = choiceRepository.findById(answerRequest.getChoiceId())
                        .orElseThrow();

                List<ChoicesEB> choices = questionEntity.getChoices().stream().toList();

                ChoicesEB correctChoiceForQuestion = Objects.requireNonNull(choices.stream().filter(ChoicesEB::getCorrectAnswer)
                                .findAny().orElseThrow(() ->
                                new InternalServerErrorException("Don't have correct answer, something wrong with the question")));

                boolean isCorrectChoice = userGivenChoice.getId().equals(correctChoiceForQuestion.getId());

                // Create a new answer if the question not answered, if not, will update the existing answer
                savedAnswer = answersRepository.save(answerRequest.createOrUpdateAnswer(questionEntity,
                        userGivenChoice,
                        sessionEntity,
                        isCorrectChoice,
                        correctChoiceForQuestion.getChoiceText()));

            } else {
                throw new UnauthorizedException("You are not allowed to create an Answer");
            }

            return ResponseEntity.ok(savedAnswer.getId().intValue());
        } catch (UnauthorizedException | NoContentException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerErrorException("Unexpected error occurred while creating an Answer");
        }
    }

    public ResponseEntity<List<AnswersResponse>> getAnswersForSessionId(String sessionId) {
        try {
            List<AnswersEB> results = answersRepository.findAnswersBySession_Id(Long.parseLong(sessionId));
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
