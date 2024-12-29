package com.example.squiz.dtos;

import com.example.squiz.entities.SessionsEB;
import com.example.squiz.entities.QuizEB;

import java.time.LocalDateTime;

public class SessionRequest {
    private Integer quizId;

    public SessionRequest() {
    }

    public SessionsEB createSessionEntity(QuizEB quizEntity, String username) {
        SessionsEB newSession = new SessionsEB();
        LocalDateTime timeNow = LocalDateTime.now();
        LocalDateTime updatedTime = timeNow.plusMinutes(quizEntity.getTimeDuration());
        newSession.setQuiz(quizEntity);
        newSession.setUserId(username);
        newSession.setCompleted(false);
        newSession.setStartTime(timeNow);
        newSession.setEndTime(updatedTime);
        newSession.setSubmitTime(updatedTime);
        newSession.setDuration(quizEntity.getTimeDuration());

        return newSession;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }
}
