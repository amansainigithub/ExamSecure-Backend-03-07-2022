package com.exam.secure.adminControllers.categoryController;

import com.exam.secure.adminControllers.adminUrlMappings.CategoryUrlMappings;
import com.exam.secure.adminControllers.adminUrlMappings.RootMapping;
import com.exam.secure.entities.categoryEntities.BranchModel;
import com.exam.secure.interfaces.categoryInterfaces.BranchInterface;
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
public class BranchController {

    @Autowired
    private BranchInterface branchInterface;

    @PostMapping(CategoryUrlMappings.CREATE_BRANCH)
    public ResponseEntity<?> createBranch(@RequestBody BranchModel branchModel)
    {
        BranchModel data =  this.branchInterface.saveBranch(branchModel);

        if(data != null)
        {
            return ResponseEntity.ok(data);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


    @PostMapping(CategoryUrlMappings.UPDATE_BRANCH)
    public ResponseEntity<?> updateBranch(@RequestBody BranchModel branchModel)
    {
        BranchModel data =  this.branchInterface.saveBranch(branchModel);

        if(data != null)
        {
            return ResponseEntity.ok(data);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }



    @GetMapping(CategoryUrlMappings.GET_BRANCH_BY_ID)
    public ResponseEntity<?> getBranchById(@PathVariable Long branchId)
    {
      BranchModel branchModel =   this.branchInterface.getBranchById(branchId);
        if( branchModel != null)
        {
            return ResponseEntity.ok(branchModel);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }




    @DeleteMapping(CategoryUrlMappings.DELETE_BRANCH_BY_ID)
    public ResponseEntity<?> deleteBranchById(@PathVariable Long branchId)
    {
        if( this.branchInterface.deleteBranchById(branchId))
        {
            return ResponseEntity.ok(new MessageResponse("delete success"));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping(CategoryUrlMappings.GET_ALL_BRANCH)
        public ResponseEntity<?> getAllBranch()
    {
        List<BranchModel> branchList =  this.branchInterface.getAllBranch();
        if( branchList != null)
        {
            return ResponseEntity.ok(branchList);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }


    @GetMapping(CategoryUrlMappings.GET_BRANCH_LIST_BY_BOTTOM_CATEGORY_ID)
    public ResponseEntity<?> getBranchListByBottomCategoryId(@PathVariable Long bottomCategoryId)
    {
        List<BranchModel> data =  this.branchInterface.getBranchListByBottomCategoryId(bottomCategoryId);
        if(data != null)
        {
            return ResponseEntity.ok(data);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @PostMapping(CategoryUrlMappings.UPLOAD_BRANCH_CATEGORY_FILE)
    public ResponseEntity<?> uploadBranchCategoryFile(@RequestParam MultipartFile file, @PathVariable("id") Long id)
    {
        BranchModel branchData =  this.branchInterface.uploadBranchCategoryFile(file,id);

        if(branchData != null)
        {
            return ResponseEntity.ok(branchData);
        }
        else{
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }
}
