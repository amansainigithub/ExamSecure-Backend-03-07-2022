package com.exam.secure.publicAllowanceController.publicCategoryController;

import com.exam.secure.adminControllers.adminUrlMappings.CategoryUrlMappings;
import com.exam.secure.customModels.CustomSubCategoryModel;
import com.exam.secure.entities.categoryEntities.QuestionSetsModel;
import com.exam.secure.entities.categoryEntities.SubCategoryModel;
import com.exam.secure.interfaces.categoryInterfaces.SubCategoryInterface;
import com.exam.secure.publicAllowanceController.urlMappings.UrlMappingPublic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(UrlMappingPublic.API_PUBLIC_AUTH_CRED)
public class PublicSubCategoryController {

    @Autowired
    private SubCategoryInterface subCategoryInterface;

    @GetMapping(UrlMappingPublic.GET_SUB_CATEGORY_PUBLIC)
    public ResponseEntity<?> getSubCategoryPublic()
    {
        List<SubCategoryModel> data =  this.subCategoryInterface.getAllSubCategory();
        if(data != null)
        {
            return ResponseEntity.ok(data);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping(UrlMappingPublic.GET_SUB_CATEGORY_List_BY_ROOT_CATEGORY_ID_PUBLIC)
    public ResponseEntity<?> getSubCategoryListByRootCategoryIdPublic(@PathVariable Long id)
    {
        List<SubCategoryModel> data =  this.subCategoryInterface.getSubCategoriesByRootCategoryId(id);
        if(data != null)
        {
            return ResponseEntity.ok(data);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


    @GetMapping(CategoryUrlMappings.GET_ALL_SETS_BY_SUB_ID)
    public ResponseEntity<?> getAllSetsBySubId(@PathVariable Long id)
    {
        List<CustomSubCategoryModel> customList =  this.subCategoryInterface.getAllSetsBySubId(id);
        if(customList != null)
        {
            return ResponseEntity.ok(customList);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


}
