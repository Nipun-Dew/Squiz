package com.example.squiz.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity @Table(name="t_answers")
public class Answers {
    @Id @SequenceGenerator(name = "answers_sequence", sequenceName = "answers_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "answers_sequence")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "answerset_id", referencedColumnName = "id")
    private AnswerSets answerSet;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Questions questions;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "choice_id", referencedColumnName = "id")
    private Choices choices;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AnswerSets getAnswerSet() {
        return answerSet;
    }

    public void setAnswerSet(AnswerSets answerSet) {
        this.answerSet = answerSet;
    }

    public Questions getQuestions() {
        return questions;
    }

    public void setQuestions(Questions questions) {
        this.questions = questions;
    }

    public Choices getChoices() {
        return choices;
    }

    public void setChoices(Choices choices) {
        this.choices = choices;
    }
}
