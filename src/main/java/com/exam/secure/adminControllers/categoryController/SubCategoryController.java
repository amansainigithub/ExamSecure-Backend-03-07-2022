package com.exam.secure.adminControllers.categoryController;

import com.exam.secure.adminControllers.adminUrlMappings.CategoryUrlMappings;
import com.exam.secure.adminControllers.adminUrlMappings.RootMapping;
import com.exam.secure.entities.categoryEntities.SubCategoryModel;
import com.exam.secure.interfaces.categoryInterfaces.SubCategoryInterface;
import com.exam.secure.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(RootMapping.ROOT_ADMIN_MAPPING)
@PreAuthorize("hasRole('ADMIN')")
public class SubCategoryController {

    @Autowired
    private SubCategoryInterface subCategoryInterface;

    @PostMapping(CategoryUrlMappings.CREATE_SUB_CATEGORY)
    public ResponseEntity<?> saveRootCategory(@RequestBody SubCategoryModel subCategoryModel)
    {
        SubCategoryModel subData =  this.subCategoryInterface.saveSubCategoyModel(subCategoryModel);

        if(subData != null)
        {
            return ResponseEntity.ok(subData);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @PutMapping(CategoryUrlMappings.UPDATE_SUB_CATEGORY)
    public ResponseEntity<?> updateSubCategory(@RequestBody SubCategoryModel subCategoryModel)
    {
        SubCategoryModel subData =  this.subCategoryInterface.saveSubCategoyModel(subCategoryModel);

        if(subData != null)
        {
            return ResponseEntity.ok(subData);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @DeleteMapping(CategoryUrlMappings.DELETE_SUB_CATEGORY_BY_ID)
    public ResponseEntity<?> deleteSubCategoryById(@PathVariable Long subCategoryId)
    {
        if(this.subCategoryInterface.deleteSubCategoryById(subCategoryId))
        {
            return ResponseEntity.ok(new MessageResponse("Delete Success"));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping(CategoryUrlMappings.GET_ALL_SUB_CATEGORY)
    public ResponseEntity<?> getAllSubCategory()
    {
        List<SubCategoryModel> subCategoryData =  this.subCategoryInterface.getAllSubCategory();
        if(subCategoryData != null)
        {
            return ResponseEntity.ok(subCategoryData);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping(CategoryUrlMappings.GET_SUB_CATEGORY_BY_ID)
    public ResponseEntity<?> getSubCategoryById(@PathVariable Long subCategoryId)
    {
       SubCategoryModel subCategoryData =  this.subCategoryInterface.getSubCategoryById(subCategoryId);
        if(subCategoryData != null)
        {
            return ResponseEntity.ok(subCategoryData);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping(CategoryUrlMappings.GET_SUB_CATEGORY_BY_ROOT_CATEGORY_ID)
    public ResponseEntity<?> getSubCategoriesByRootCategoryId(@PathVariable Long rootCategoryId)
    {
        List<SubCategoryModel> data =  this.subCategoryInterface.getSubCategoriesByRootCategoryId(rootCategoryId);
        if(data != null)
        {
            return ResponseEntity.ok(data);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping(CategoryUrlMappings.UPLOAD_SUB_CATEGORY_FILE)
    public ResponseEntity<?> uploadSubCategoryFile(@RequestParam MultipartFile file, @PathVariable("id") Long id)
    {
        SubCategoryModel subData =  this.subCategoryInterface.uploadSubCategoryFile(file,id);

        if(subData != null)
        {
            return ResponseEntity.ok(subData);
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


}
