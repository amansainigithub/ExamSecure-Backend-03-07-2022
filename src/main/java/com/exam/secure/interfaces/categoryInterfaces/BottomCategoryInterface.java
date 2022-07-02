package com.exam.secure.interfaces.categoryInterfaces;

import com.exam.secure.entities.categoryEntities.BottomCategoryModel;
import com.exam.secure.publicAllowanceController.modelTemp.BottomTempModel;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public interface BottomCategoryInterface {
    public BottomCategoryModel saveBottomCategory(BottomCategoryModel bottomCategoryModel);

    public List<BottomCategoryModel> getAllBottomCategory();

    boolean deleteBottomCategoryById(Long bottomCategoryId);

    BottomCategoryModel getBottomCategoryById(Long bottomCategoryId);

    List<BottomCategoryModel> getBottomCategoriesBySubCategoryId(Long subCategoryId);

    BottomCategoryModel uploadBottomCategoryFile(MultipartFile file, Long id);

    List<BottomTempModel> getBottomCategoriesBySubCategoryId_RC(Long subCategoryId); //Response Changed
}
