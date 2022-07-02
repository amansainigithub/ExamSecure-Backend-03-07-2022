package com.exam.secure.repository.categoryRepository;

import com.exam.secure.entities.categoryEntities.QuestionSetsModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionSetsRepository extends JpaRepository<QuestionSetsModel,Long> {

    @Query("SELECT u FROM QuestionSetsModel u WHERE u.chaptersModel.id = :id")
    List<QuestionSetsModel> getQuestionSetByChapterId(@Param("id") Long id);

}
