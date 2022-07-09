package com.exam.secure.publicAllowanceController.publicCategoryController;

import com.exam.secure.adminControllers.adminUrlMappings.CategoryUrlMappings;
import com.exam.secure.customModels.CustomRootModel;
import com.exam.secure.entities.categoryEntities.RootCategoryModel;
import com.exam.secure.interfaces.categoryInterfaces.BottomCategoryInterface;
import com.exam.secure.interfaces.categoryInterfaces.RootCategoryInterface;
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
public class PublicRootCategoryController {

    @Autowired
    private RootCategoryInterface rootCategoryInterface;

    @GetMapping(UrlMappingPublic.GET_ROOT_CATEGORY_LIST_PUBLIC)
    public ResponseEntity<?> getRootCategoryListPublic()
    {
        List<CustomRootModel> data =  this.rootCategoryInterface.getAllRootCategoryPublic();
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
