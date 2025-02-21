package com.example.squiz.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.util.Set;

@Entity
@Table(name = "t_choices")
public class ChoicesEB {
    @Id
    @SequenceGenerator(name = "choices_sequence", sequenceName = "choices_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "choices_sequence")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private QuestionsEB questions;

    @OneToMany(mappedBy = "choice")
    private Set<AnswersEB> answers;

    private String choiceNumber;
    private String choiceText;
    private String helperText;
    private String imageUrl;
    private String imageName;
    private Boolean correctAnswer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuestionsEB getQuestions() {
        return questions;
    }

    public void setQuestions(QuestionsEB questions) {
        this.questions = questions;
    }

    public Set<AnswersEB> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswersEB> answers) {
        this.answers = answers;
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

    public void setImageUrl(String attachments) {
        this.imageUrl = attachments;
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
