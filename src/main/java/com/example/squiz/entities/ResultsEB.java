package com.example.squiz.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "t_results")
public class ResultsEB {
    @Id
    @SequenceGenerator(name = "results_sequence", sequenceName = "results_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "results_sequence")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private QuizEB quiz;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "session_id", referencedColumnName = "id")
    private SessionsEB session;

    private Integer numberOfCorrectAnswers;

    private Integer numberOfIncorrectAnswers;

    private Integer timeTaken;

    public SessionsEB getSession() {
        return session;
    }

    public void setSession(SessionsEB session) {
        this.session = session;
    }

    public QuizEB getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizEB quiz) {
        this.quiz = quiz;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumberOfCorrectAnswers() {
        return numberOfCorrectAnswers;
    }

    public void setNumberOfCorrectAnswers(Integer numberOfCorrectAnswers) {
        this.numberOfCorrectAnswers = numberOfCorrectAnswers;
    }

    public Integer getNumberOfIncorrectAnswers() {
        return numberOfIncorrectAnswers;
    }

    public void setNumberOfIncorrectAnswers(Integer numberOfIncorrectAnswers) {
        this.numberOfIncorrectAnswers = numberOfIncorrectAnswers;
    }

    public Integer getTimeTaken() {
        return timeTaken;
    }

    public void setTimeTaken(Integer timeTaken) {
        this.timeTaken = timeTaken;
    }
}
