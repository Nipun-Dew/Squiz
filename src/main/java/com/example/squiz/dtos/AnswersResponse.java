package com.example.squiz.dtos;

import com.example.squiz.entities.AnswersEB;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AnswersResponse {
    private Integer questionId;
    private Integer choiceId;
    private Integer answerSetId;
    private LocalDateTime submitTime;

    public Integer getQuestionId() {
        return questionId;
    }

    public AnswersResponse createAnswerResponse(AnswersEB answerEntity) {
        AnswersResponse response = new AnswersResponse();
        response.setQuestionId(answerEntity.getQuestions().getId().intValue());
        response.setQuestionId(answerEntity.getChoices().getId().intValue());
        response.setQuestionId(answerEntity.getAnswerSet().getId().intValue());
        response.setSubmitTime(answerEntity.getSubmitTime());

        return response;
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

    public Integer getAnswerSetId() {
        return answerSetId;
    }

    public void setAnswerSetId(Integer answerSetId) {
        this.answerSetId = answerSetId;
    }

    public LocalDateTime getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(LocalDateTime submitTime) {
        this.submitTime = submitTime;
    }

    public AnswersResponse() {
    }
}
