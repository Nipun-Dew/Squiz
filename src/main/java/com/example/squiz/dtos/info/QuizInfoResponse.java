package com.example.squiz.dtos.info;

import com.example.squiz.dtos.QuizResponse;
import com.example.squiz.entities.QuizEB;

import java.util.List;

public class QuizInfoResponse {
    private QuizResponse quiz;
    private List<QuestionsInfoResponse> questions;

    public QuizInfoResponse() {
    }

    public QuizInfoResponse createQuizInfoResponse(QuizEB quizEntity) {
        this.setQuiz(new QuizResponse().createQuizResponse(quizEntity));
        this.setQuestions(quizEntity.getQuestions().stream().map(question ->
                new QuestionsInfoResponse().createQuestionInfoResponse(question)).toList());

        return this;
    }

    public QuizResponse getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizResponse quiz) {
        this.quiz = quiz;
    }

    public List<QuestionsInfoResponse> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionsInfoResponse> questions) {
        this.questions = questions;
    }
}
