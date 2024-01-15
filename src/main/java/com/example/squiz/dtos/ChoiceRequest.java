package com.example.squiz.dtos;

import com.example.squiz.entities.ChoicesEB;
import com.example.squiz.entities.QuestionsEB;

public class ChoiceRequest {
    private Integer questionId;
    private String choiceNumber;
    private String choiceText;
    private String helperText;
    private Boolean attachments;
    private Boolean correctAnswer;

    public ChoiceRequest() {
    }

    public ChoicesEB createChoiceEntity(QuestionsEB questionEntity) {
        ChoicesEB newChoice = new ChoicesEB();
        newChoice.setQuestions(questionEntity);
        newChoice.setChoiceNumber(choiceNumber);
        newChoice.setChoiceText(choiceText);
        newChoice.setHelperText(helperText);
        newChoice.setAttachments(attachments);
        newChoice.setCorrectAnswer(correctAnswer);

        return newChoice;
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
