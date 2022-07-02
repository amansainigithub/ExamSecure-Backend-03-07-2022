package com.exam.secure.entities.categoryEntities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class QuestionSetsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String QuestionSetName;

    @Column(length = 500)
    private String defaultName;

    @Column(length = 500)
    private String title;

    private String experience;

    @Column(length = 2000)
    private String paragraph;

    private String passPercentage;

    private String questionSetDuration;

    @Column(length = 3000)
    private String shortDescription;

    @Column(length = 5000)
    private String longDescription;

    private boolean status = false;

    @Column(length = 1000)
    private String fileUrl;

    @Column(length = 1000)
    private String fileName;

    private String bgColor;

    private String storingDateTime;

    private String sequenceNum;

    private String questionSetCloneId;

    @ManyToOne(fetch = FetchType.EAGER)
    private ChaptersModel chaptersModel;

    @OneToMany(mappedBy = "questionSetsModel",cascade = CascadeType.ALL)
    @JsonIgnore
    private List<QuestionsAnswerModel> questionsAnswerModel ;
}
