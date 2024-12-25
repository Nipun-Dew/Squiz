package com.example.squiz.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "t_quiz")
public class QuizEB {
    @Id
    @SequenceGenerator(name = "quiz_sequence", sequenceName = "quiz_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quiz_sequence")
    private Long id;

    @OneToMany(mappedBy = "quiz")
    private Set<QuestionsEB> questions;

    @OneToMany(mappedBy = "quiz")
    private Set<AnswerSetsEB> answerSets;

    private String creatorId;
    private String title;
    private String description;
    private String identifier;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;
    private Integer timeDuration;
    private String state;
    private LocalDateTime dueDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<QuestionsEB> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<QuestionsEB> questions) {
        this.questions = questions;
    }

    public Set<AnswerSetsEB> getAnswerSets() {
        return answerSets;
    }

    public void setAnswerSets(Set<AnswerSetsEB> answerSets) {
        this.answerSets = answerSets;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
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
