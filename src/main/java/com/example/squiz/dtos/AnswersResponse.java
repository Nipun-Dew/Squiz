package com.example.squiz.dtos;

import com.example.squiz.entities.AnswersEB;

import java.time.LocalDateTime;

public class AnswersResponse {
    private Long id;
    private Integer questionId;
    private Integer choiceId;
    private Integer sessionId;
    private LocalDateTime submitTime;
    private Boolean isCorrectAnswer;
    private String correctAnswer;

    public AnswersResponse createAnswerResponse(AnswersEB answerEntity) {
        this.setId(answerEntity.getId());
        this.setQuestionId(answerEntity.getQuestions().getId().intValue());
        this.setChoiceId(answerEntity.getChoices().getId().intValue());
        this.setSessionId(answerEntity.getSession().getId().intValue());
        this.setSubmitTime(answerEntity.getSubmitTime());
        this.setCorrectAnswer(answerEntity.getCorrectAnswer());
        this.setIsCorrectAnswer(answerEntity.getIsCorrectAnswer());

        return this;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDateTime getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(LocalDateTime submitTime) {
        this.submitTime = submitTime;
    }

    public Boolean getIsCorrectAnswer() {
        return isCorrectAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Boolean correctAnswer) {
        isCorrectAnswer = correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setIsCorrectAnswer(Boolean isCorrectAnswer) {
        this.isCorrectAnswer = isCorrectAnswer;
    }

    public AnswersResponse() {
    }
}
