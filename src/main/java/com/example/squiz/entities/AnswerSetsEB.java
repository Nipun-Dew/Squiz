package com.example.squiz.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Table(name = "t_answersets")
public class AnswerSetsEB {
    @Id
    @SequenceGenerator(name = "answersets_sequence", sequenceName = "answersets_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answersets_sequence")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private QuizEB quiz;

    @OneToMany(mappedBy = "answerSets")
    private Set<AnswersEB> answers;

    private String creatorId;
    private LocalDateTime startTime;
    private Integer duration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuizEB getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizEB quiz) {
        this.quiz = quiz;
    }

    public Set<AnswersEB> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswersEB> answers) {
        this.answers = answers;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime submitTime) {
        this.startTime = submitTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
