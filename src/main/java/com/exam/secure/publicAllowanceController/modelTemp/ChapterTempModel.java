package com.exam.secure.publicAllowanceController.modelTemp;

import com.exam.secure.entities.categoryEntities.BranchModel;
import lombok.Data;
import java.util.List;

@Data
public class ChapterTempModel {

    private long id;

    private String chapterName;


    private String defaultName;


    private String shortDescription;


    private String longDescription;

    private boolean status = false;

    private String fileUrl;

    private String fileName;

    private String bgColor;

    private String storingDateTime;

    private String sequenceNum;

    private String branchCloneId;


    private BranchModel branchModel;

    private List<QuestionSetTempModel> questionSetsModels ;
}
