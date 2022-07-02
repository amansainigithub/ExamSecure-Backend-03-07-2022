package com.exam.secure.entities.categoryEntities;

import lombok.Data;

@Data
public class ResultModel {

    private int correctAnswer;
    private int wrongAnswer;
    private int totalQuestion;
    private int attemptQuestions;
    private String timeDuration;
    private float percentage;


}
