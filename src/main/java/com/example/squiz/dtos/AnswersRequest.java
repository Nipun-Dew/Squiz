package com.example.squiz.dtos;

import com.example.squiz.entities.SessionsEB;
import com.example.squiz.entities.AnswersEB;
import com.example.squiz.entities.ChoicesEB;
import com.example.squiz.entities.QuestionsEB;

import java.time.LocalDateTime;

public class AnswersRequest {
    // If user not provided an id in answer request, it will create an new answer, else update the answer for the given id
    private Long id = 0L;
    private Long questionId;
    private Long choiceId;
    private Long sessionId;

    public AnswersRequest() {
    }

    public AnswersEB createOrUpdateAnswer(QuestionsEB questionEntity,
                                          ChoicesEB choiceEntity,
                                          SessionsEB sessionEntity,
                                          Boolean isCorrectAnswer,
                                          String correctAnswer) {
        AnswersEB newAnswer = new AnswersEB();
        // If id has a value in answer request, this method will update the existing answer for the given id
        newAnswer.setId(id);
        newAnswer.setQuestion(questionEntity);
        newAnswer.setChoice(choiceEntity);
        newAnswer.setSession(sessionEntity);
        newAnswer.setIsCorrectAnswer(isCorrectAnswer);
        newAnswer.setCorrectAnswer(correctAnswer);
        newAnswer.setSubmitTime(LocalDateTime.now());

        return newAnswer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(Long choiceId) {
        this.choiceId = choiceId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }
}
