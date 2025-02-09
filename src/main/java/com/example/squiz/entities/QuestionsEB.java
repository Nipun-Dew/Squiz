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
@Table(name = "t_questions")
public class QuestionsEB {
    @Id
    @SequenceGenerator(name = "question_sequence", sequenceName = "question_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "question_sequence")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "quiz_id", referencedColumnName = "id")
    private QuizEB quiz;

    @OneToMany(mappedBy = "question")
    private Set<AnswersEB> answers;

    @OneToMany(mappedBy = "questions")
    private Set<ChoicesEB> choices;

    private String questionNumber;
    private String question;
    private String imageUrl;
    private String imageName;
    private Integer allocatedTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuizEB getQuiz() {
        return quiz;
    }

    public void setQuiz(QuizEB quiz) {
        this.quiz = quiz;
    }

    public Set<AnswersEB> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswersEB> answers) {
        this.answers = answers;
    }

    public Set<ChoicesEB> getChoices() {
        return choices;
    }

    public void setChoices(Set<ChoicesEB> choices) {
        this.choices = choices;
    }

    public String getQuestionNumber() {
        return questionNumber;
    }

    public void setQuestionNumber(String questionNumber) {
        this.questionNumber = questionNumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String attachments) {
        this.imageUrl = attachments;
    }

    public Integer getAllocatedTime() {
        return allocatedTime;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setAllocatedTime(Integer allocatedTime) {
        this.allocatedTime = allocatedTime;
    }
}
