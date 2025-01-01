package com.example.squiz.dtos.info;

import com.example.squiz.dtos.ChoiceResponse;
import com.example.squiz.dtos.QuestionsResponse;
import com.example.squiz.entities.QuestionsEB;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QuestionsInfoResponse {
    private QuestionsResponse question;
    private List<ChoiceResponse> answers;

    public QuestionsInfoResponse() {
    }

    public QuestionsInfoResponse createQuestionInfoResponse(QuestionsEB questionEntity) {
        List<ChoiceResponse> answers = new ArrayList<>(questionEntity.getChoices().stream().map(choice ->
                new ChoiceResponse().createChoiceResponse(choice)).toList());

        answers.sort(Comparator.comparingLong(ChoiceResponse::getId));

        this.setQuestion(new QuestionsResponse().createQuestionsResponse(questionEntity));
        this.setAnswers(answers);

        return this;
    }

    public QuestionsResponse getQuestion() {
        return question;
    }

    public void setQuestion(QuestionsResponse question) {
        this.question = question;
    }

    public List<ChoiceResponse> getAnswers() {
        return answers;
    }

    public void setAnswers(List<ChoiceResponse> answers) {
        this.answers = answers;
    }
}
