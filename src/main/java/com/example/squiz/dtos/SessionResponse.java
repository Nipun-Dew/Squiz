package com.example.squiz.dtos;

import com.example.squiz.entities.SessionsEB;

import java.time.LocalDateTime;

public class SessionResponse {
    private Integer id;
    private Integer quizId;
    private String quizIdentifier;
    private String userId;
    private Boolean isCompleted;
    private LocalDateTime startTime;
    private LocalDateTime submitTime;
    private LocalDateTime endTime;
    private Integer duration;

    public SessionResponse() {
    }

    public SessionResponse createSessionResponse(SessionsEB sessionEntity) {
        this.setId(sessionEntity.getId().intValue());
        this.setQuizId(sessionEntity.getQuiz().getId().intValue());
        this.setQuizIdentifier(sessionEntity.getQuiz().getIdentifier());
        this.setUserId(sessionEntity.getUserId());
        this.setCompleted(sessionEntity.getCompleted());
        this.setStartTime(sessionEntity.getStartTime());
        this.setSubmitTime(sessionEntity.getSubmitTime());
        this.setEndTime(sessionEntity.getEndTime());
        this.setDuration(sessionEntity.getDuration());

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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public String getQuizIdentifier() {
        return quizIdentifier;
    }

    public void setQuizIdentifier(String quizIdentifier) {
        this.quizIdentifier = quizIdentifier;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public LocalDateTime getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(LocalDateTime submitTime) {
        this.submitTime = submitTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
