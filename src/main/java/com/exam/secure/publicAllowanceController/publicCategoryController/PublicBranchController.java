package com.exam.secure.publicAllowanceController.publicCategoryController;

import com.exam.secure.customModels.CustomBranchModel;
import com.exam.secure.entities.categoryEntities.BranchModel;
import com.exam.secure.interfaces.categoryInterfaces.BranchInterface;
import com.exam.secure.publicAllowanceController.urlMappings.UrlMappingPublic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(UrlMappingPublic.API_PUBLIC_AUTH_CRED)
public class PublicBranchController {

    @Autowired
    private BranchInterface branchInterface;

    @GetMapping(UrlMappingPublic.GET_BRANCH_CATEGORY_PUBLIC)
    public ResponseEntity<?> getBranchCategoryPublic()
    {
        List<BranchModel> data =  this.branchInterface.getAllBranch();
        if(data != null)
        {
            return ResponseEntity.ok(data);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @GetMapping(UrlMappingPublic.GET_BRANCH_LIST_BY_BOTTOM_CATEGORY_ID_PUBLIC)
    public ResponseEntity<?> getBranchListByBottomCategoryIdPublic(@PathVariable Long bottomCategoryId)
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


    @GetMapping(UrlMappingPublic.GET_BRANCH_LIST_BY_SUB_ID)
    public ResponseEntity<?> getBranchListBySubId(@PathVariable Long subId)
    {
        List<CustomBranchModel> data =  this.branchInterface.getBranchListBySubId(subId);
        if(data != null)
        {
            return ResponseEntity.ok(data);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }



}
