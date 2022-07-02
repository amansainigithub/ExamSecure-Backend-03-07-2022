package com.exam.secure.publicAllowanceController.modelTemp;

import com.exam.secure.entities.categoryEntities.ChaptersModel;
import com.exam.secure.entities.categoryEntities.QuestionsAnswerModel;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class QuestionSetTempModel {

    private long id;

    private String QuestionSetName;

    private String defaultName;

    private String title;

    private String experience;

    private String paragraph;

    private String passPercentage;

    private String questionSetDuration;

    private String shortDescription;

    private String longDescription;

    private boolean status = false;

    private String fileUrl;

    private String fileName;

    private String bgColor;

    private String storingDateTime;

    private String sequenceNum;

    private String questionSetCloneId;

    private ChaptersModel chaptersModel;

    private List<QuestionsAnswerModel> questionsAnswerModel ;
}
