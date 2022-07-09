package com.exam.secure.interfaces.categoryInterfaces;

import com.exam.secure.customModels.CustomSubCategoryModel;
import com.exam.secure.entities.categoryEntities.QuestionSetsModel;
import com.exam.secure.entities.categoryEntities.SubCategoryModel;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public interface SubCategoryInterface {

    public SubCategoryModel saveSubCategoyModel(SubCategoryModel subCategoryModel);

    public boolean deleteSubCategoryById(Long subCategoryId);

    public List<SubCategoryModel> getAllSubCategory();

    SubCategoryModel getSubCategoryById(Long subCategoryId);

    List<SubCategoryModel> getSubCategoriesByRootCategoryId(Long rootCategoryId);

    SubCategoryModel uploadSubCategoryFile(MultipartFile file, Long id);

    List<CustomSubCategoryModel> getAllSetsBySubId(Long id);
}
