package com.example.squiz.dtos;

import com.example.squiz.entities.AnswerSetsEB;

import java.time.LocalDateTime;

public class AnswerSetResponse {
    private Integer id;
    private Integer quizId;
    private Long userId;
    private LocalDateTime startTime;
    private Integer duration;

    public AnswerSetResponse() {
    }

    public AnswerSetResponse createAnswerSetResponse(AnswerSetsEB answerSetEntity) {
        this.setId(answerSetEntity.getId().intValue());
        this.setQuizId(answerSetEntity.getQuiz().getId().intValue());
        this.setUserId(answerSetEntity.getUserId());
        this.setStartTime(answerSetEntity.getStartTime());
        this.setDuration(answerSetEntity.getDuration());

        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
