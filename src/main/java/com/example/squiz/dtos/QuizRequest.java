package com.example.squiz.dtos;

import com.example.squiz.entities.QuizEB;

import java.time.LocalDateTime;

public class QuizRequest {
    private String creatorId;
    private String title;
    private String description;
    private String timeDuration;
    private String state;
    private String dueDate;

    public QuizRequest() {
    }

    public QuizEB createQuizEntity(String username) {
        QuizEB newQuiz = new QuizEB();
        newQuiz.setCreatorId(username);
        newQuiz.setTitle(title);
        newQuiz.setDescription(description);
        newQuiz.setCreatedDate(LocalDateTime.now());
        newQuiz.setModifiedDate(LocalDateTime.now());
        newQuiz.setDueDate(LocalDateTime.parse(dueDate));
        newQuiz.setState(state);
        newQuiz.setTimeDuration(Integer.parseInt(timeDuration));
        return newQuiz;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }
}
