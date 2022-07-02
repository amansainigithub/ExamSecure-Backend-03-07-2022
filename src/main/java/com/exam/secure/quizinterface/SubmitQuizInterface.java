package com.exam.secure.quizinterface;

import com.exam.secure.entities.categoryEntities.ResultModel;
import com.exam.secure.publicAllowanceController.modelTemp.QuestionAnswerTempModel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface SubmitQuizInterface {

 public ResultModel submitQuizResult(List<QuestionAnswerTempModel> questionAnswerTempModels,String timeDuration);

    ResultModel sendReportToEmail(List<QuestionAnswerTempModel> questionAnswerTempModels, String timeDuration,String email);
}
