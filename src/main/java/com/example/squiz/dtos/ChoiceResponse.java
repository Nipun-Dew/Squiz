package com.example.squiz.dtos;

import com.example.squiz.entities.ChoicesEB;

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
        this.setId(choicesEntity.getId().intValue());
        this.setQuestionId(choicesEntity.getQuestions().getId().intValue());
        this.setChoiceNumber(choicesEntity.getChoiceNumber());
        this.setChoiceText(choicesEntity.getChoiceText());
        this.setHelperText(choicesEntity.getHelperText());
        this.setAttachments(choicesEntity.getAttachments());
        this.setCorrectAnswer(choicesEntity.getCorrectAnswer());

        return this;
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
