package com.example.squiz.dtos;

import com.example.squiz.entities.AnswerSetsEB;
import com.example.squiz.entities.QuizEB;

import java.time.LocalDateTime;

public class AnswerSetRequest {
    private Integer quizId;
    private Long userId;

    public AnswerSetRequest() {
    }

    public AnswerSetsEB createAnswerSetEntity(QuizEB quizEntity) {
        AnswerSetsEB newAnswerSet = new AnswerSetsEB();
        newAnswerSet.setQuiz(quizEntity);
        newAnswerSet.setUserId(userId);
        newAnswerSet.setStartTime(LocalDateTime.now());
        newAnswerSet.setDuration(0);

        return newAnswerSet;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
