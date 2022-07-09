package com.exam.secure.customModels;

import com.exam.secure.entities.categoryEntities.SubCategoryModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
@Data
public class CustomRootModel {

    private long id;

    private String rootCategoryName;

    private String defaultName;

    private String shortDescription;

    private String longDescription;

    private boolean status = false;

    private String fileUrl;

    private String fileName;

    private String bgColor;

    private String storingDateTime;

    private String sequenceNum;

    private List<SubCategoryModel> subCategoryModel;
}
