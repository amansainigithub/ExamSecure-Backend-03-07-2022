package com.exam.secure.repository.categoryRepository;

import com.exam.secure.entities.categoryEntities.BranchModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BranchRepository extends JpaRepository<BranchModel,Long> {

        @Query("SELECT u FROM BranchModel u WHERE u.bottomCategoryModel.id = :id")
        List<BranchModel> getBranchListByBottomCategoryId(@Param("id") Long id);


        @Query("SELECT u FROM BranchModel u WHERE u.bottomCategoryModel.subCategoryModel.id = :id")
        List<BranchModel> getBranchListBySubId(@Param("id") Long id);


}
