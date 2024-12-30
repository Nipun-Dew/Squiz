package com.example.squiz.dtos.info;

public class QuestionMetadata {
    private Long questionId;
    private String questionNumber;

    public QuestionMetadata(Long questionId, String questionNumber) {
        this.questionId = questionId;
        this.questionNumber = questionNumber;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }
}
