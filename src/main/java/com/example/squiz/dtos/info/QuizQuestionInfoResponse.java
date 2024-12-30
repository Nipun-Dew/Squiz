package com.example.squiz.dtos.info;

import com.example.squiz.dtos.QuizResponse;
import com.example.squiz.entities.QuizEB;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QuizQuestionInfoResponse {
    private QuizResponse quiz;
    private List<QuestionMetadata> questionsMetadata;

    public QuizQuestionInfoResponse() {
    }

    public QuizQuestionInfoResponse createQuizResponse(QuizEB quizEntity) {
        List<QuestionMetadata> questionsMetadata = new ArrayList<>(quizEntity.getQuestions().stream().map(question ->
                new QuestionMetadata(question.getId(), question.getQuestionNumber())).toList());

        questionsMetadata.sort(Comparator.comparingLong(QuestionMetadata::getQuestionId));

        this.setQuiz(new QuizResponse().createQuizResponse(quizEntity));
        this.setQuestionsMetadata(questionsMetadata);

        return this;
    }

    public QuizResponse getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizResponse quiz) {
        this.quiz = quiz;
    }

    public List<QuestionMetadata> getQuestionsMetadata() {
        return questionsMetadata;
    }

    public void setQuestionsMetadata(List<QuestionMetadata> questionsMetadata) {
        this.questionsMetadata = questionsMetadata;
    }
}
