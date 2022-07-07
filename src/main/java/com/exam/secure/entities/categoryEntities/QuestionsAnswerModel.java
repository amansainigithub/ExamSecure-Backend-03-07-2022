package com.exam.secure.entities.categoryEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class QuestionsAnswerModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 4000)
    private String question;

    @Column(length = 1000)
    private String defaultName;

    @Column(length = 1000)
    private String title;

    @Column(length = 1000)
    private String ansKey;

    private String ansNameKey; //Like a,b,c,d

    @Column(length = 1000)
    private String option1;

    @Column(length = 1000)
    private String option2;

    @Column(length = 1000)
    private String option3;

    @Column(length = 1000)
    private String option4;

    @Column(length = 1000)
    private String solution;

    private String questionDuration;

    @Column(length = 1000)
    private String description;

    private boolean status = true;

    @Column(length = 1000)
    private String fileUrl;

    @Column(length = 1000)
    private String fileName;

    private String storingDateTime;

    private String sequenceNum;

    private String questionSetCloneId;

    @Transient
    private String givenAnswer;

    @ManyToOne(fetch = FetchType.EAGER)
    private QuestionSetsModel questionSetsModel;


    @OneToMany(mappedBy = "questionsAnswerModel",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<QuestionFiles> questionFiles ;
}
