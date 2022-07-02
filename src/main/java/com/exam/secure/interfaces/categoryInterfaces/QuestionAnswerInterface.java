package com.exam.secure.interfaces.categoryInterfaces;

import com.exam.secure.entities.categoryEntities.QuestionsAnswerModel;
import com.exam.secure.publicAllowanceController.modelTemp.QuestionAnswerTempModel;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QuestionAnswerInterface {

        public QuestionsAnswerModel saveQuestionAnswer(QuestionsAnswerModel questionsAnswerModel);

        public List<QuestionsAnswerModel> getAllQuestionAnswer();

        public boolean deleteQuestionAnswerById(Long id);

        public QuestionsAnswerModel getQuestionAnswerById(Long id);

        List<QuestionsAnswerModel> getQuestionAnswerByQuestionSetId( Long id);

        List<QuestionAnswerTempModel> getQuestionAnswerListByQuestionSetIdPublic_RC(Long id);

        Page<QuestionsAnswerModel> getQuestionListPagination(int pageNo, int pageSize);
}
