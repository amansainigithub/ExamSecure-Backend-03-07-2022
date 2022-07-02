package com.exam.secure.publicAllowanceController.publicCategoryController;


import com.exam.secure.adminControllers.adminUrlMappings.CategoryUrlMappings;
import com.exam.secure.interfaces.categoryInterfaces.BottomCategoryInterface;
import com.exam.secure.publicAllowanceController.modelTemp.BottomTempModel;
import com.exam.secure.publicAllowanceController.urlMappings.UrlMappingPublic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(UrlMappingPublic.API_PUBLIC_AUTH_CRED)
public class PublicBottomCategoryController {

    @Autowired
    private BottomCategoryInterface bottomCategoryInterface;

    @GetMapping(CategoryUrlMappings.GET_BOTTOM_CATEGORY_BY_SUB_CATEGORY_ID_RC)
    public ResponseEntity<?> getBottomCategoriesBySubCategoryIdRc(@PathVariable Long subCategoryId)
    {
        List<BottomTempModel> data =  this.bottomCategoryInterface.getBottomCategoriesBySubCategoryId_RC(subCategoryId);
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
