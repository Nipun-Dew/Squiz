package com.example.squiz.dtos;

import com.example.squiz.entities.ChoicesEB;
import org.springframework.stereotype.Component;

@Component
public class ChoiceResponse {
    private Integer id;
    private Integer questionId;
    private String choiceNumber;
    private String choiceText;
    private String helperText;
    private Boolean attachments;
    private Boolean correctAnswer;

    public ChoiceResponse() {
    }

    public ChoiceResponse createChoiceResponse(ChoicesEB choicesEntity) {
        ChoiceResponse response = new ChoiceResponse();
        response.setId(choicesEntity.getId().intValue());
        response.setQuestionId(choicesEntity.getQuestions().getId().intValue());
        response.setChoiceNumber(choicesEntity.getChoiceNumber());
        response.setChoiceText(choicesEntity.getChoiceText());
        response.setHelperText(choicesEntity.getHelperText());
        response.setAttachments(choicesEntity.getAttachments());
        response.setCorrectAnswer(choicesEntity.getCorrectAnswer());

        return response;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public String getChoiceNumber() {
        return choiceNumber;
    }

    public void setChoiceNumber(String choiceNumber) {
        this.choiceNumber = choiceNumber;
    }

    public String getChoiceText() {
        return choiceText;
    }

    public void setChoiceText(String choiceText) {
        this.choiceText = choiceText;
    }

    public String getHelperText() {
        return helperText;
    }

    public void setHelperText(String helperText) {
        this.helperText = helperText;
    }

    public Boolean getAttachments() {
        return attachments;
    }

    public void setAttachments(Boolean attachments) {
        this.attachments = attachments;
    }

    public Boolean getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
}
