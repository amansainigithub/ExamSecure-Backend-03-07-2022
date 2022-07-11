package com.exam.secure.customModels;
import com.exam.secure.entities.categoryEntities.BranchModel;
import com.exam.secure.entities.categoryEntities.SubCategoryModel;
import lombok.Data;

import java.util.List;

@Data
public class CustomBottomModel {
    private long id;

    private String bottomCategoryName;

    private String defaultName;

    private String shortDescription;

    private String longDescription;

    private boolean status = false;

    private String fileUrl;

    private String fileName;

    private String bgColor;

    private String storingDateTime;

    private String sequenceNum;

    private String subCategoryCloneId;

    private SubCategoryModel subCategoryModel;

    private List<BranchModel> branchModel ;

}
