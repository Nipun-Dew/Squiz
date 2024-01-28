package com.example.squiz.dtos;

import com.example.squiz.entities.AnswersEB;

import java.time.LocalDateTime;

public class AnswersResponse {
    private Integer questionId;
    private Integer choiceId;
    private Integer answerSetId;
    private LocalDateTime submitTime;

    public AnswersResponse createAnswerResponse(AnswersEB answerEntity) {
        this.setQuestionId(answerEntity.getQuestions().getId().intValue());
        this.setQuestionId(answerEntity.getChoices().getId().intValue());
        this.setQuestionId(answerEntity.getAnswerSet().getId().intValue());
        this.setSubmitTime(answerEntity.getSubmitTime());

        return this;
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
