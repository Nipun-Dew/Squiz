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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private QuestionsEB questions;

    @OneToMany(mappedBy = "answerSets")
    private Set<AnswersEB> answers;

    private Long userId;
    private LocalDateTime submitTime;
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

    public QuestionsEB getQuestions() {
        return questions;
    }

    public void setQuestions(QuestionsEB questions) {
        this.questions = questions;
    }

    public Set<AnswersEB> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswersEB> answers) {
        this.answers = answers;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(LocalDateTime submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
