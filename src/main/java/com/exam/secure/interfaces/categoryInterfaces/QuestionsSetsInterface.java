package com.exam.secure.interfaces.categoryInterfaces;

import com.exam.secure.entities.categoryEntities.QuestionSetsModel;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public interface QuestionsSetsInterface {

    public QuestionSetsModel saveQuestionSet(QuestionSetsModel questionSetsModel);

    public List<QuestionSetsModel> getAllQuestionSets();

    public boolean deleteQuestionSetById(Long id);

    public QuestionSetsModel getQuestionSetById(Long id);

    public List<QuestionSetsModel> getQuestionSetByChapterId(Long id);

    QuestionSetsModel uploadQuestionSetFile(MultipartFile file, Long id);
}
