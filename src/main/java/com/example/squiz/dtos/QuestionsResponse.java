package com.example.squiz.dtos;

import com.example.squiz.entities.QuestionsEB;
import org.springframework.stereotype.Component;

@Component
public class QuestionsResponse {
    private Integer id;
    private Integer quizId;
    private String questionNumber;
    private String question;
    private Boolean attachments;
    private Integer allocatedTime;

    public QuestionsResponse() {
    }

    public QuestionsResponse createQuestionsResponse(QuestionsEB questionEntity) {
        QuestionsResponse response = new QuestionsResponse();
        response.setId(questionEntity.getId().intValue());
        response.setQuizId(questionEntity.getQuiz().getId().intValue());
        response.setQuestionNumber(questionEntity.getQuestionNumber());
        response.setQuestion(questionEntity.getQuestion());
        response.setAttachments(questionEntity.getAttachments());
        response.setAllocatedTime(questionEntity.getAllocatedTime());

        return response;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
