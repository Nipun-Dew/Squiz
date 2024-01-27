package com.example.squiz.dtos;

import com.example.squiz.entities.AnswerSetsEB;
import com.example.squiz.entities.AnswersEB;
import com.example.squiz.entities.ChoicesEB;
import com.example.squiz.entities.QuestionsEB;

import java.time.LocalDateTime;

public class AnswersRequest {
    private Integer questionId;
    private Integer choiceId;
    private Integer answerSetId;

    public AnswersRequest() {
    }

    public AnswersEB createAnswerEntity(QuestionsEB questionEntity,
                                        ChoicesEB choiceEntity,
                                        AnswerSetsEB answerSetEntity) {
        AnswersEB newAnswer = new AnswersEB();
        newAnswer.setQuestions(questionEntity);
        newAnswer.setChoices(choiceEntity);
        newAnswer.setAnswerSet(answerSetEntity);
        newAnswer.setSubmitTime(LocalDateTime.now());

        return newAnswer;
    }

    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    public Integer getChoiceId() {
        return choiceId;
    }

    public void setChoiceId(Integer choiceId) {
        this.choiceId = choiceId;
    }

    public Integer getAnswerSetId() {
        return answerSetId;
    }

    public void setAnswerSetId(Integer answerSetId) {
        this.answerSetId = answerSetId;
    }
}
