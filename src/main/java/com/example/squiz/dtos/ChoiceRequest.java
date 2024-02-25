package com.example.squiz.dtos;

import com.example.squiz.entities.ChoicesEB;
import com.example.squiz.entities.QuestionsEB;

public class ChoiceRequest {
    private Integer questionId;
    private String choiceNumber;
    private String choiceText;
    private String helperText;
    private String imageUrl;
    private String imageName;
    private Boolean correctAnswer;

    public ChoiceRequest() {
    }

    public ChoicesEB createChoiceEntity(QuestionsEB questionEntity) {
        ChoicesEB newChoice = new ChoicesEB();
        newChoice.setQuestions(questionEntity);
        newChoice.setChoiceNumber(choiceNumber);
        newChoice.setChoiceText(choiceText);
        newChoice.setHelperText(helperText);
        newChoice.setImageUrl(imageUrl);
        newChoice.setImageName(imageName);
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Boolean getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(Boolean correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
