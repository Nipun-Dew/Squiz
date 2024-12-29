package com.example.squiz.dtos;

import com.example.squiz.entities.SessionsEB;
import com.example.squiz.entities.AnswersEB;
import com.example.squiz.entities.ChoicesEB;
import com.example.squiz.entities.QuestionsEB;

import java.time.LocalDateTime;

public class AnswersRequest {
    private Integer questionId;
    private Integer choiceId;
    private Integer sessionId;

    public AnswersRequest() {
    }

    public AnswersEB createAnswerEntity(QuestionsEB questionEntity,
                                        ChoicesEB choiceEntity,
                                        SessionsEB sessionEntity,
                                        Boolean isCorrectAnswer,
                                        String correctAnswer) {
        AnswersEB newAnswer = new AnswersEB();
        newAnswer.setQuestions(questionEntity);
        newAnswer.setChoices(choiceEntity);
        newAnswer.setSession(sessionEntity);
        newAnswer.setIsCorrectAnswer(isCorrectAnswer);
        newAnswer.setCorrectAnswer(correctAnswer);
        newAnswer.setSubmitTime(LocalDateTime.now());

        return newAnswer;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(Integer choiceId) {
        this.choiceId = choiceId;
    }

    public Integer getSessionId() {
        return sessionId;
    }

    public void setSessionId(Integer sessionId) {
        this.sessionId = sessionId;
    }
}
