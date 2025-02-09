package com.example.squiz.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_answers", uniqueConstraints = @UniqueConstraint(columnNames = {"session_id", "question_id"}))
public class AnswersEB {
    @Id
    @SequenceGenerator(name = "answers_sequence", sequenceName = "answers_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answers_sequence")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private QuestionsEB question;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "choice_id", referencedColumnName = "id")
    private ChoicesEB choice;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private SessionsEB session;

    private LocalDateTime submitTime;

    private Boolean isCorrectAnswer;

    private String correctAnswer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SessionsEB getSession() {
        return session;
    }

    public void setSession(SessionsEB session) {
        this.session = session;
    }

    public QuestionsEB getQuestion() {
        return question;
    }

    public void setQuestion(QuestionsEB questions) {
        this.question = questions;
    }

    public ChoicesEB getChoice() {
        return choice;
    }

    public void setChoice(ChoicesEB choices) {
        this.choice = choices;
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

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public void setIsCorrectAnswer(Boolean isCorrectAnswer) {
        this.isCorrectAnswer = isCorrectAnswer;
    }
}
