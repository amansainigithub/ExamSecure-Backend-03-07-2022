package com.exam.secure.customModels;

import com.exam.secure.entities.categoryEntities.ChaptersModel;
import com.exam.secure.entities.categoryEntities.QuestionsAnswerModel;
import java.util.List;

public class CustomSubCategoryModel {

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

    private int questionLength;


    public long getId() {
        return id;
    }

    public String getQuestionSetName() {
        return QuestionSetName;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public String getTitle() {
        return title;
    }

    public String getExperience() {
        return experience;
    }

    public String getParagraph() {
        return paragraph;
    }

    public String getPassPercentage() {
        return passPercentage;
    }

    public String getQuestionSetDuration() {
        return questionSetDuration;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public String getLongDescription() {
        return longDescription;
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

    public String getBgColor() {
        return bgColor;
    }

    public String getStoringDateTime() {
        return storingDateTime;
    }

    public String getSequenceNum() {
        return sequenceNum;
    }

    public String getQuestionSetCloneId() {
        return questionSetCloneId;
    }

    public ChaptersModel getChaptersModel() {
        return chaptersModel;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setQuestionSetName(String questionSetName) {
        QuestionSetName = questionSetName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public void setParagraph(String paragraph) {
        this.paragraph = paragraph;
    }

    public void setPassPercentage(String passPercentage) {
        this.passPercentage = passPercentage;
    }

    public void setQuestionSetDuration(String questionSetDuration) {
        this.questionSetDuration = questionSetDuration;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
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

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public void setStoringDateTime(String storingDateTime) {
        this.storingDateTime = storingDateTime;
    }

    public void setSequenceNum(String sequenceNum) {
        this.sequenceNum = sequenceNum;
    }

    public void setQuestionSetCloneId(String questionSetCloneId) {
        this.questionSetCloneId = questionSetCloneId;
    }

    public void setChaptersModel(ChaptersModel chaptersModel) {
        this.chaptersModel = chaptersModel;
    }

    public int getQuestionLength() {
        return questionLength;
    }

    public void setQuestionLength(int questionLength) {
        this.questionLength = questionLength;
    }
}
