package com.exam.secure.entities.categoryEntities;

import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class QuestionFiles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(length = 3000)
    private String fileName;

    @Column(length = 3000)
    private String fileUrl;

    @ManyToOne
    private QuestionsAnswerModel questionsAnswerModel;
}
