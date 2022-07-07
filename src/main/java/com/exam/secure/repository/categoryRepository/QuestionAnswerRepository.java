package com.exam.secure.repository.categoryRepository;

import com.exam.secure.entities.categoryEntities.QuestionsAnswerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionAnswerRepository extends JpaRepository<QuestionsAnswerModel,Long> {

    @Query("SELECT u FROM QuestionsAnswerModel u WHERE u.questionSetsModel.id = :id")
    List<QuestionsAnswerModel> getQuestionAnswerByQuestionSetId(@Param("id") Long id);

    List<QuestionsAnswerModel> findTop10ByOrderByIdDesc();
}
