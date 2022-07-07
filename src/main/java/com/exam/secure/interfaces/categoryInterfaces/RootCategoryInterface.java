package com.exam.secure.interfaces.categoryInterfaces;

import com.exam.secure.entities.categoryEntities.QuestionSetsModel;
import com.exam.secure.entities.categoryEntities.QuestionsAnswerModel;
import com.exam.secure.entities.categoryEntities.RootCategoryModel;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public interface RootCategoryInterface {

    public RootCategoryModel saveRootCategory(RootCategoryModel rootCategoryModel);

   public  boolean deleteRootCategoryById(Long rootCategoryId);

   public List<RootCategoryModel> getAllRootCategory();

    RootCategoryModel getRootCategoryById(Long rootCategoryId);

    RootCategoryModel uploadRootCategoryFile(MultipartFile file , Long id);

    List<RootCategoryModel> searchByRootCategoryKey(String key);

    List<QuestionSetsModel> getAllSetsByRootId(Long id);

}
