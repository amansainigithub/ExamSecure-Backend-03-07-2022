package com.exam.secure.publicAllowanceController.publicCategoryController;

import com.exam.secure.interfaces.categoryInterfaces.ChaptersInterface;
import com.exam.secure.publicAllowanceController.modelTemp.ChapterTempModel;
import com.exam.secure.publicAllowanceController.urlMappings.UrlMappingPublic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(UrlMappingPublic.API_PUBLIC_AUTH_CRED)
public class publicChapterController {

    @Autowired
    private ChaptersInterface chaptersInterface;

    @GetMapping(UrlMappingPublic.GET_CHAPTERS_BY_BRANCH_CATEGORY_ID)
    public ResponseEntity<?> getChaptersByBranchCategoryId(@PathVariable Long branchId)
    {
        List<ChapterTempModel> data =  this.chaptersInterface.getChaptersByBranchCategoryId(branchId,true);

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
