package com.exam.secure.publicAllowanceController.modelTemp;

import com.exam.secure.entities.categoryEntities.QuestionFiles;
import com.exam.secure.entities.categoryEntities.QuestionSetsModel;
import lombok.Data;
import java.util.List;

@Data

public class QuestionAnswerTempModel {

    private long id;

    private String question;

    private String defaultName;

    private String title;

    private String ansKey;

    private String ansNameKey; //Like a,b,c,d

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private String solution;

    private String questionDuration;

    private String description;

    private boolean status = false;

    private String fileUrl;

    private String fileName;

    private String storingDateTime;

    private String sequenceNum;

    private String givenAnswer;

    private String timeDuration;

    private String questionSetCloneId;

    private QuestionSetsModel questionSetsModel;


    private List<QuestionFiles> questionFiles ;

    public long getId() {
        return id;
    }

    public String getQuestion() {
        return question;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public String getTitle() {
        return title;
    }

    public String getAnsKey() {
        return ansKey;
    }

    public String getAnsNameKey() {
        return ansNameKey;
    }

    public String getOption1() {
        return option1;
    }

    public String getOption2() {
        return option2;
    }

    public String getOption3() {
        return option3;
    }

    public String getOption4() {
        return option4;
    }

    public String getSolution() {
        return solution;
    }

    public String getQuestionDuration() {
        return questionDuration;
    }

    public String getDescription() {
        return description;
    }

    public boolean isStatus() {
        return status;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public String getStoringDateTime() {
        return storingDateTime;
    }

    public String getSequenceNum() {
        return sequenceNum;
    }

    public String getGivenAnswer() {
        return givenAnswer;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    public String getQuestionSetCloneId() {
        return questionSetCloneId;
    }

    public QuestionSetsModel getQuestionSetsModel() {
        return questionSetsModel;
    }

    public List<QuestionFiles> getQuestionFiles() {
        return questionFiles;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAnsKey(String ansKey) {
        this.ansKey = ansKey;
    }

    public void setAnsNameKey(String ansNameKey) {
        this.ansNameKey = ansNameKey;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public void setQuestionDuration(String questionDuration) {
        this.questionDuration = questionDuration;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setStoringDateTime(String storingDateTime) {
        this.storingDateTime = storingDateTime;
    }

    public void setSequenceNum(String sequenceNum) {
        this.sequenceNum = sequenceNum;
    }

    public void setGivenAnswer(String givenAnswer) {
        this.givenAnswer = givenAnswer;
    }

    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
    }

    public void setQuestionSetCloneId(String questionSetCloneId) {
        this.questionSetCloneId = questionSetCloneId;
    }

    public void setQuestionSetsModel(QuestionSetsModel questionSetsModel) {
        this.questionSetsModel = questionSetsModel;
    }

    public void setQuestionFiles(List<QuestionFiles> questionFiles) {
        this.questionFiles = questionFiles;
    }
}
