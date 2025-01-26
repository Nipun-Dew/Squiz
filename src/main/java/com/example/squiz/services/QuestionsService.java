package com.example.squiz.services;

import com.example.squiz.dtos.AnswersResponse;
import com.example.squiz.dtos.info.QuestionsInfoRequest;
import com.example.squiz.dtos.info.QuestionsInfoResponse;
import com.example.squiz.entities.AnswersEB;
import com.example.squiz.entities.ChoicesEB;
import com.example.squiz.entities.QuestionsEB;
import com.example.squiz.entities.QuizEB;
import com.example.squiz.exceptions.customExceptions.InternalServerErrorException;
import com.example.squiz.exceptions.customExceptions.NoContentException;
import com.example.squiz.repos.AnswersRepository;
import com.example.squiz.repos.ChoiceRepository;
import com.example.squiz.repos.QuestionsRepository;
import com.example.squiz.repos.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.lang.Long.parseLong;

@Service
public class QuestionsService {
    private final QuestionsRepository questionsRepository;
    private final QuizRepository quizRepository;
    private final ChoiceRepository choiceRepository;
    private final AnswersRepository answersRepository;

    @Autowired
    public QuestionsService(QuestionsRepository questionsRepository,
                            QuizRepository quizRepository,
                            ChoiceRepository choiceRepository, AnswersRepository answersRepository) {
        this.questionsRepository = questionsRepository;
        this.quizRepository = quizRepository;
        this.choiceRepository = choiceRepository;
        this.answersRepository = answersRepository;
    }

    public ResponseEntity<QuestionsInfoResponse> getQuestionInfo(String id) {
        try {
            Optional<QuestionsEB> optionalQuestionInfo = questionsRepository.findById(parseLong(id));

            return optionalQuestionInfo.map(question -> ResponseEntity.ok(new QuestionsInfoResponse().createQuestionInfoResponse(question)))
                    .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).body(new QuestionsInfoResponse()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new QuestionsInfoResponse());
        }
    }

    public ResponseEntity<QuestionsInfoResponse> getQuestionInfoWithAnswer(String questionId,
                                                                           String sessionId) {
        try {
            Optional<QuestionsEB> optionalQuestion = questionsRepository.findById(parseLong(questionId));

            Optional<AnswersEB> optionalAnswer = answersRepository.findByQuestionIdAndSessionId(parseLong(questionId),
                    parseLong(sessionId));

            if (optionalQuestion.isEmpty()) {
                throw new NoContentException("No question found with id " + questionId);
            }

            QuestionsInfoResponse questionsInfoResponse = new QuestionsInfoResponse()
                    .createQuestionInfoResponse(optionalQuestion.get());

            if (optionalAnswer.isPresent()) {
                List<AnswersResponse> answersResponse = new ArrayList<>();
                answersResponse.add(new AnswersResponse().createAnswerResponse(optionalAnswer.get()));
                questionsInfoResponse.setAnswers(answersResponse);
            } else {
                questionsInfoResponse.setAnswers(new ArrayList<>());
            }

            return ResponseEntity.ok(questionsInfoResponse);
        } catch (NoContentException e) {
            throw e;
        } catch (Exception e) {
            throw new InternalServerErrorException("An unexpected error occurred while finding the quiz: " + e.getMessage());
        }
    }

    @Transactional
    public ResponseEntity<Integer> createNewQuestion(QuestionsInfoRequest questionsInfoRequest, String username) {
        try {
            QuestionsEB savedQuestion;
            QuizEB quizEntity = quizRepository.findById(questionsInfoRequest.getQuestion().getQuizId().longValue()).orElseThrow();

            if (quizEntity.getCreatorId().equals(username)) {
                savedQuestion = questionsRepository.save(questionsInfoRequest.getQuestion().createQuestionEntity(quizEntity));
                choiceRepository.saveAll(questionsInfoRequest.getAnswers().stream().map(
                        req -> req.createChoiceEntity(savedQuestion)).toList());
            } else {
                throw new Exception("User is not the creator of the relevant Quiz");
            }
            return ResponseEntity.ok(savedQuestion.getId().intValue());

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(-1);
        }
    }

    public ResponseEntity<Integer> updateQuestion(QuestionsInfoRequest questionInfoRequest,
                                                  String username) {
        try {
            QuestionsEB savedQuestion;

            QuizEB quizEntity = quizRepository.findById(questionInfoRequest.getQuestion().getQuizId().longValue())
                    .orElseThrow();
            QuestionsEB questionEntity = questionInfoRequest.getQuestion().createQuestionEntity(quizEntity);
            questionEntity.setId(questionInfoRequest.getQuestion().getQuestionId());

            if (quizEntity.getCreatorId().equals(username)) {
                savedQuestion = questionsRepository.save(questionEntity);

                List<ChoicesEB> choices = questionInfoRequest.getAnswers().stream().map(choice -> {
                    ChoicesEB choiceEntity = choice.createChoiceEntity(savedQuestion);
                    choiceEntity.setId(choice.getChoiceId());
                    return choiceEntity;
                }).toList();

                choiceRepository.saveAll(choices);
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
            List<QuestionsEB> results = questionsRepository.findQuestionsByQuiz_Id(parseLong(quizId));
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
