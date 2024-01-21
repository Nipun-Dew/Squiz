package com.example.squiz.dtos;

import com.example.squiz.entities.AnswerSetsEB;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AnswerSetResponse {
    private Integer id;
    private Integer quizId;
    private Long userId;
    private LocalDateTime startTime;
    private Integer duration;

    public AnswerSetResponse() {
    }

    public AnswerSetResponse createAnswerSetResponse(AnswerSetsEB answerSetEntity) {
        AnswerSetResponse response = new AnswerSetResponse();
        response.setId(answerSetEntity.getId().intValue());
        response.setQuizId(answerSetEntity.getQuiz().getId().intValue());
        response.setUserId(answerSetEntity.getUserId());
        response.setStartTime(answerSetEntity.getStartTime());
        response.setDuration(answerSetEntity.getDuration());

        return response;
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
