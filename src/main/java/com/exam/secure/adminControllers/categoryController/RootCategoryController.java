package com.exam.secure.adminControllers.categoryController;

import com.exam.secure.adminControllers.adminUrlMappings.CategoryUrlMappings;
import com.exam.secure.adminControllers.adminUrlMappings.RootMapping;
import com.exam.secure.entities.categoryEntities.QuestionSetsModel;
import com.exam.secure.entities.categoryEntities.QuestionsAnswerModel;
import com.exam.secure.entities.categoryEntities.RootCategoryModel;
import com.exam.secure.interfaces.categoryInterfaces.RootCategoryInterface;
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
public class RootCategoryController {

    @Autowired
    private RootCategoryInterface rootCategoryInterface;

    @PostMapping(CategoryUrlMappings.CREATE_ROOT_CATEGORY)
    public ResponseEntity<?> saveRootCategory(@RequestBody RootCategoryModel rootCategoryModel)
    {
        RootCategoryModel rootData =  this.rootCategoryInterface.saveRootCategory(rootCategoryModel);

         if(rootData != null)
         {
             return ResponseEntity.ok(rootData);
         }
         else
         {
             return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
         }
    }

    @PutMapping(CategoryUrlMappings.UPDATE_ROOT_CATEGORY)
    public ResponseEntity<?> updateRootCategory(@RequestBody RootCategoryModel rootCategoryModel)
    {
        RootCategoryModel rootData =  this.rootCategoryInterface.saveRootCategory(rootCategoryModel);

        if(rootData != null)
        {
            return ResponseEntity.ok(rootData);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @DeleteMapping(CategoryUrlMappings.DELETE_ROOT_CATEGORY_BY_ID)
    public ResponseEntity<?> deleteRootCategoryById(@PathVariable Long rootCategoryId)
    {
        if(this.rootCategoryInterface.deleteRootCategoryById(rootCategoryId))
        {
            return ResponseEntity.ok(new MessageResponse("Delete Success"));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping(CategoryUrlMappings.GET_ALL_ROOT_CATEGORY)
    public ResponseEntity<?> getAllRootCategory() {
        List<RootCategoryModel> rootCategoryList =this.rootCategoryInterface.getAllRootCategory();
        if( rootCategoryList != null)
        {
            return ResponseEntity.ok(rootCategoryList);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(CategoryUrlMappings.GET_ROOT_CATEGORY_BY_ID)
    public ResponseEntity<?> getRootCategoryById(@PathVariable Long rootCategoryId)
    {
        RootCategoryModel rootCategoryData =  this.rootCategoryInterface.getRootCategoryById(rootCategoryId);
        if(rootCategoryData != null)
        {
            return ResponseEntity.ok(rootCategoryData);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


    @GetMapping(CategoryUrlMappings.GET_ALL_SETS_BY_ROOT_ID)
    public ResponseEntity<?> getAllSetsByRootId(@PathVariable Long id)
    {
        List<QuestionSetsModel> rootCategoryData =  this.rootCategoryInterface.getAllSetsByRootId(id);
        if(rootCategoryData != null)
        {
            return ResponseEntity.ok(rootCategoryData);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping(CategoryUrlMappings.SEARCH_BY_ROOT_CATEGORY_KEY)
    public ResponseEntity<?> searchByRootCategoryKey(@PathVariable String key)
    {
        List<RootCategoryModel> rootCategoryData =  this.rootCategoryInterface.searchByRootCategoryKey(key);
        if(rootCategoryData != null)
        {
            return ResponseEntity.ok(rootCategoryData);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


    @PostMapping(CategoryUrlMappings.UPLOAD_ROOT_CATEGORY_FILE)
    public ResponseEntity<?> uploadRootCategoryFile(@RequestParam MultipartFile file,@PathVariable("id") Long id)
    {
        RootCategoryModel rootData =  this.rootCategoryInterface.uploadRootCategoryFile(file,id);

        if(rootData != null)
        {
            return ResponseEntity.ok(rootData);
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

}
