package com.example.squiz.dtos.info;

import com.example.squiz.dtos.QuizResponse;
import com.example.squiz.entities.QuizEB;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class QuizInfoResponse {
    private QuizResponse quiz;
    private List<QuestionsInfoResponse> questions;

    public QuizInfoResponse() {
    }

    public QuizInfoResponse createQuizInfoResponse(QuizEB quizEntity) {
        Stream<QuestionsInfoResponse> questions = quizEntity.getQuestions().stream().map(question ->
                new QuestionsInfoResponse().createQuestionInfoResponse(question));

        Stream<QuestionsInfoResponse> sortedQuestions = questions.sorted(
                Comparator.comparingLong(question -> question.getQuestion().getId()));

        this.setQuiz(new QuizResponse().createQuizResponse(quizEntity));
        this.setQuestions(sortedQuestions.toList());

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
