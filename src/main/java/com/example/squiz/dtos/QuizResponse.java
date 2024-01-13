package com.example.squiz.dtos;

import com.example.squiz.entities.QuizEB;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class QuizResponse {
    private Integer id;
    private Integer creatorId;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Integer timeDuration;
    private String state;
    private LocalDateTime dueDate;

    public QuizResponse() {
    }

    public QuizResponse createQuizResponse(QuizEB quizEntity) {
        QuizResponse response = new QuizResponse();
        response.setId(quizEntity.getId().intValue());
        response.setCreatorId(quizEntity.getCreatorId());
        response.setCreatedDate(quizEntity.getCreatedDate());
        response.setModifiedDate(quizEntity.getModifiedDate());
        response.setTimeDuration(quizEntity.getTimeDuration());
        response.setState(quizEntity.getState());
        response.setDueDate(quizEntity.getDueDate());

        return response;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public LocalDateTime getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(LocalDateTime modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(Integer timeDuration) {
        this.timeDuration = timeDuration;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LocalDateTime getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDateTime dueDate) {
        this.dueDate = dueDate;
    }
}
