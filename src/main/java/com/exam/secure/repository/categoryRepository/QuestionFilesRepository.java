package com.exam.secure.repository.categoryRepository;

import com.exam.secure.entities.categoryEntities.QuestionFiles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionFilesRepository extends JpaRepository<QuestionFiles,Long> {

    @Query("SELECT u FROM QuestionFiles u WHERE u.questionsAnswerModel.id = :id")
    List<QuestionFiles> getQuestionFilesByQuestionAnswerId(@Param("id") Long id);
}
