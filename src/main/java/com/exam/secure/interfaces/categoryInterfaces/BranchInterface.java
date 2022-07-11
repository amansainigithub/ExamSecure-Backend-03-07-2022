package com.exam.secure.interfaces.categoryInterfaces;

import com.exam.secure.customModels.CustomBranchModel;
import com.exam.secure.entities.categoryEntities.BranchModel;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public interface BranchInterface {

   public BranchModel saveBranch(BranchModel branchModel);


    public boolean deleteBranchById(Long id);

    public List<BranchModel> getAllBranch();

    public BranchModel getBranchById(Long branchId);

    List<BranchModel> getBranchListByBottomCategoryId(Long bottomCategoryId);

   List<CustomBranchModel> getBranchListBySubId(Long subId);

    BranchModel uploadBranchCategoryFile(MultipartFile file, Long id);
}
