package com.example.squiz.dtos;

import com.example.squiz.entities.QuestionsEB;
import com.example.squiz.entities.QuizEB;

public class QuestionsRequest {
    private Integer quizId;
    private String questionNumber;
    private String question;
    private Boolean attachments;
    private Integer allocatedTime;

    public QuestionsRequest() {
    }

    public QuestionsEB createQuestionEntity(QuizEB quizEntity) {
        QuestionsEB newQuestion = new QuestionsEB();
        newQuestion.setQuiz(quizEntity);
        newQuestion.setQuestionNumber(questionNumber);
        newQuestion.setQuestion(question);
        newQuestion.setAttachments(attachments);
        newQuestion.setAllocatedTime(allocatedTime);

        return newQuestion;
    }

    public Integer getQuizId() {
        return quizId;
    }

    public void setQuizId(Integer quizId) {
        this.quizId = quizId;
    }

    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Boolean getAttachments() {
        return attachments;
    }

    public void setAttachments(Boolean attachments) {
        this.attachments = attachments;
    }

    public Integer getAllocatedTime() {
        return allocatedTime;
    }

    public void setAllocatedTime(Integer allocatedTime) {
        this.allocatedTime = allocatedTime;
    }
}
