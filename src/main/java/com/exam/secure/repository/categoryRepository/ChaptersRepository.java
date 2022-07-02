package com.exam.secure.repository.categoryRepository;

import com.exam.secure.entities.categoryEntities.ChaptersModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChaptersRepository extends JpaRepository<ChaptersModel,Long> {

    @Query("SELECT u FROM ChaptersModel u WHERE u.branchModel.id = :id")
    List<ChaptersModel> getBottomCategoryByIdService(@Param("id") Long id);


    @Query("SELECT u FROM ChaptersModel u WHERE u.branchModel.id = :id and u.status = :status")
    List<ChaptersModel> getChaptersByBranchCategoryId(@Param("id") Long id,@Param("status") boolean status);



}
