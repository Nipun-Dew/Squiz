package com.example.squiz.dtos;

import com.example.squiz.entities.AnswerSetsEB;
import com.example.squiz.entities.QuizEB;

import java.time.LocalDateTime;

public class AnswerSetRequest {
    private Integer quizId;

    public AnswerSetRequest() {
    }

    public AnswerSetsEB createAnswerSetEntity(QuizEB quizEntity, String username) {
        AnswerSetsEB newAnswerSet = new AnswerSetsEB();
        newAnswerSet.setQuiz(quizEntity);
        newAnswerSet.setCreatorId(username);
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
}
