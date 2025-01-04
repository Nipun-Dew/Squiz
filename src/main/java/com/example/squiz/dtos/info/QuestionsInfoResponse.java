package com.example.squiz.dtos.info;

import com.example.squiz.dtos.AnswersResponse;
import com.example.squiz.dtos.ChoiceResponse;
import com.example.squiz.dtos.QuestionsResponse;
import com.example.squiz.entities.QuestionsEB;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QuestionsInfoResponse {
    private QuestionsResponse question;
    private List<ChoiceResponse> choices;
    private List<AnswersResponse> answers;

    public QuestionsInfoResponse() {
    }

    public QuestionsInfoResponse createQuestionInfoResponse(QuestionsEB questionEntity) {
        List<ChoiceResponse> choices = new ArrayList<>(questionEntity.getChoices().stream().map(choice ->
                new ChoiceResponse().createChoiceResponse(choice)).toList());

        if (questionEntity.getAnswers() != null && !questionEntity.getAnswers().isEmpty()) {
            this.setAnswers(questionEntity.getAnswers().stream().map(answer ->
                    new AnswersResponse().createAnswerResponse(answer)).toList());
        }

        choices.sort(Comparator.comparingLong(ChoiceResponse::getId));

        this.setQuestion(new QuestionsResponse().createQuestionsResponse(questionEntity));
        this.setChoices(choices);

        return this;
    }

    public QuestionsResponse getQuestion() {
        return question;
    }

    public void setQuestion(QuestionsResponse question) {
        this.question = question;
    }

    public List<ChoiceResponse> getChoices() {
        return choices;
    }

    public void setChoices(List<ChoiceResponse> choices) {
        this.choices = choices;
    }

    public List<AnswersResponse> getAnswers() {
        return answers;
    }

    public void setAnswers(List<AnswersResponse> answers) {
        this.answers = answers;
    }
}
