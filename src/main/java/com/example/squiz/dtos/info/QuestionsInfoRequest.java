package com.example.squiz.dtos.info;

import com.example.squiz.dtos.ChoiceRequest;
import com.example.squiz.dtos.QuestionsRequest;

import java.util.List;

public class QuestionsInfoRequest {
    private QuestionsRequest question;
    private List<ChoiceRequest> answers;

    public QuestionsInfoRequest() {
    }

    public QuestionsRequest getQuestion() {
        return question;
    }

    public void setQuestion(QuestionsRequest question) {
        this.question = question;
    }

    public List<ChoiceRequest> getAnswers() {
        return answers;
    }

    public void setAnswers(List<ChoiceRequest> answers) {
        this.answers = answers;
    }
}
