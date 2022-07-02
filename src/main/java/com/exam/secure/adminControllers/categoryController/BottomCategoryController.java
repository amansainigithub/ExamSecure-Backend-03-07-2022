package com.exam.secure.adminControllers.categoryController;

import com.exam.secure.adminControllers.adminUrlMappings.CategoryUrlMappings;
import com.exam.secure.adminControllers.adminUrlMappings.RootMapping;
import com.exam.secure.entities.categoryEntities.BottomCategoryModel;
import com.exam.secure.interfaces.categoryInterfaces.BottomCategoryInterface;
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
public class BottomCategoryController {

    @Autowired
    private BottomCategoryInterface bottomCategoryInterface;

    @PostMapping(CategoryUrlMappings.CREATE_BOTTOM_CATEGORY)
    public ResponseEntity<?> createBottomCategory(@RequestBody BottomCategoryModel bottomCategoryModel)
    {
        BottomCategoryModel data =  this.bottomCategoryInterface.saveBottomCategory(bottomCategoryModel);

        if(data != null)
        {
            return ResponseEntity.ok(data);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @PutMapping(CategoryUrlMappings.UPDATE_BOTTOM_CATEGORY)
    public ResponseEntity<?> updateBottomCategory(@RequestBody BottomCategoryModel bottomCategoryModel)
    {
        BottomCategoryModel rootData =  this.bottomCategoryInterface.saveBottomCategory(bottomCategoryModel);

        if(rootData != null)
        {
            return ResponseEntity.ok(rootData);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping(CategoryUrlMappings.GET_ALL_BOTTOM_CATEGORY)
    public ResponseEntity<?> getAllBottomCategory()
    {
        List<BottomCategoryModel> rootData =  this.bottomCategoryInterface.getAllBottomCategory();

        if(rootData != null)
        {
            return ResponseEntity.ok(rootData);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @DeleteMapping(CategoryUrlMappings.DELETE_BOTTOM_CATEGORY_BY_ID)
    public ResponseEntity<?> deleteBottomCategoryById(@PathVariable Long bottomCategoryId)
    {
        if( this.bottomCategoryInterface.deleteBottomCategoryById(bottomCategoryId))
        {
            return ResponseEntity.ok(new MessageResponse("Delete Success"));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


    @GetMapping(CategoryUrlMappings.GET_BOTTOM_CATEGORY_BY_ID)
    public ResponseEntity<?> getBottomCategoryById(@PathVariable Long bottomCategoryId)
    {
        BottomCategoryModel bottomCategoryModel =  this.bottomCategoryInterface.getBottomCategoryById(bottomCategoryId);

        if(bottomCategoryModel != null)
        {
            return ResponseEntity.ok(bottomCategoryModel);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


    @GetMapping(CategoryUrlMappings.GET_BOTTOM_CATEGORY_BY_SUB_CATEGORY_ID)
    public ResponseEntity<?> getBottomCategoriesBySubCategoryId(@PathVariable Long subCategoryId)
    {
        List<BottomCategoryModel> data =  this.bottomCategoryInterface.getBottomCategoriesBySubCategoryId(subCategoryId);
        if(data != null)
        {
            return ResponseEntity.ok(data);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @PostMapping(CategoryUrlMappings.UPLOAD_BOTTOM_CATEGORY_FILE)
    public ResponseEntity<?> uploadBottomCategoryFile(@RequestParam MultipartFile file, @PathVariable("id") Long id)
    {
        BottomCategoryModel bottomData =  this.bottomCategoryInterface.uploadBottomCategoryFile(file,id);

        if(bottomData != null)
        {
            return ResponseEntity.ok(bottomData);
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }
}
