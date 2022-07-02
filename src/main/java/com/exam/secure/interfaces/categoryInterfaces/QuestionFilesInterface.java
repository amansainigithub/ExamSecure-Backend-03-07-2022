package com.exam.secure.interfaces.categoryInterfaces;

import com.exam.secure.entities.categoryEntities.QuestionFiles;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public interface QuestionFilesInterface {

    QuestionFiles uploadQuestionFiles(MultipartFile file , Long questionAnswerId);

    List<QuestionFiles> getQuestionsFilesByQuestionAnswerId(Long questionAnswerId);

    boolean deleteQuestionFileById(Long id);

    QuestionFiles updateQuestionFile(MultipartFile file, Long id);
}
