package com.example.squiz.dtos;

import com.example.squiz.entities.ResultsEB;

public class ResultsResponse {
    private Integer id;
    private QuizResponse quizResponse;
    private SessionResponse sessionResponse;
    private Integer correctNoOfAnswers;
    private Integer incorrectNoOfAnswers;
    private Integer duration;

    public ResultsResponse() {}

    public ResultsResponse createResultsResponse(ResultsEB resultsEntity) {
        this.setId(resultsEntity.getId().intValue());
        this.setQuizResponse(new QuizResponse().createQuizResponse(resultsEntity.getQuiz()));
        this.setSessionResponse(new SessionResponse().createSessionResponse(resultsEntity.getSession()));
        this.setCorrectNoOfAnswers(resultsEntity.getNumberOfCorrectAnswers());
        this.setIncorrectNoOfAnswers(resultsEntity.getNumberOfIncorrectAnswers());
        this.setDuration(resultsEntity.getTimeTaken());

        return this;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public QuizResponse getQuizResponse() {
        return quizResponse;
    }

    public void setQuizResponse(QuizResponse quizResponse) {
        this.quizResponse = quizResponse;
    }

    public SessionResponse getSessionResponse() {
        return sessionResponse;
    }

    public void setSessionResponse(SessionResponse sessionResponse) {
        this.sessionResponse = sessionResponse;
    }

    public Integer getCorrectNoOfAnswers() {
        return correctNoOfAnswers;
    }

    public void setCorrectNoOfAnswers(Integer correctNoOfAnswers) {
        this.correctNoOfAnswers = correctNoOfAnswers;
    }

    public Integer getIncorrectNoOfAnswers() {
        return incorrectNoOfAnswers;
    }

    public void setIncorrectNoOfAnswers(Integer incorrectNoOfAnswers) {
        this.incorrectNoOfAnswers = incorrectNoOfAnswers;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}
